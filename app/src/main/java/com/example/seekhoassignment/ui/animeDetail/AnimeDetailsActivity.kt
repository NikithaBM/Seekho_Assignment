package com.example.seekhoassignment.ui.animeDetail

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seekhoassignment.R
import com.example.seekhoassignment.adapter.AnimeAdapter
import com.example.seekhoassignment.databinding.ActivityAnimeDetailsBinding
import com.example.seekhoassignment.databinding.ActivityAnimeListBinding
import com.example.seekhoassignment.ui.animeList.AnimeListViewModel
import com.example.seekhoassignment.utils.Resource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityAnimeDetailsBinding
    private val mAnimeListVM: AnimeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if(intent.hasExtra("animeId")){
            fetchAnimeDetails(intent.getIntExtra("animeId", 0))
        }
        mBinding.backIv.setOnClickListener { v: View? ->
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }

    private fun fetchAnimeDetails(stringExtra: Int?) {
        mAnimeListVM.fetchAnimeDetails(stringExtra!!)
        mAnimeListVM.animeDetails.observe(this){
            when(it){
                is Resource.Error -> {
                    mBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this@AnimeDetailsActivity, "Unable to load the Anime. Please try again.", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    mBinding.progressBar.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    mBinding.progressBar.visibility = View.GONE
                   mBinding.tvAnimeTitle.text = it.data?.data?.title
                    mBinding.tvPlot.text = "Plot: \n" +it.data?.data?.synopsis
                    mBinding.tvRating.text ="Rating: "+ it.data?.data?.score.toString()
                    var genreStr =""
                    it.data?.data?.genres?.forEach {
                        genreStr +=  it.name + ", "
                    }

                    mBinding.tvGenre.text ="Genres: " + genreStr.substringBeforeLast(",")
                    mBinding.tvEpisodes.text ="Episodes: " + it.data?.data?.episodes.toString()

                    mBinding.trailerVideoView.addYouTubePlayerListener(
                        object :
                            AbstractYouTubePlayerListener() {
                            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {

                                it.data?.data?.trailer?.url?.let { it1 ->
                                    youTubePlayer.loadVideo(
                                        it1, 0f)
                                }
                                youTubePlayer.play()
                                youTubePlayer.setLoop(true)
                                youTubePlayer.mute()
                            }
                        })

                }
            }
        }

    }

}