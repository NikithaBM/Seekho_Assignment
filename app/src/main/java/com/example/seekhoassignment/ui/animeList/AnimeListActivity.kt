package com.example.seekhoassignment.ui.animeList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.seekhoassignment.adapter.AnimeAdapter
import com.example.seekhoassignment.databinding.ActivityAnimeListBinding
import com.example.seekhoassignment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeListActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityAnimeListBinding
    private val mAnimeListVM: AnimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.backIv.setOnClickListener { v: View? ->
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        fetchAnimeList()
    }

    private fun fetchAnimeList() {
        mAnimeListVM.animeList.observe(this){
            when(it){
                is Resource.Error -> {
                    mBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this@AnimeListActivity, "Unable to load the Anime. Please try again.", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    mBinding.progressBar.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    mBinding.progressBar.visibility = View.GONE
                    val adapter = it.data?.let { it1 -> AnimeAdapter(this@AnimeListActivity, it1) }
                    mBinding.rvAnimeList.adapter = adapter

                }
            }
        }
    }
}