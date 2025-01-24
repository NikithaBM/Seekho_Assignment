package com.example.seekhoassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekhoassignment.data.model.AnimePOJO
import com.example.seekhoassignment.databinding.ItemAnimeBinding
import com.example.seekhoassignment.ui.animeDetail.AnimeDetailsActivity

class AnimeAdapter(private val mContext: Context, private val animeList: AnimePOJO) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder( val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            ItemAnimeBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return AnimeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val animeItem = animeList.data[position]
        holder.binding.tvTitle.text = animeItem.title
        holder.binding.tvEpisodes.text = "Episodes: ${animeItem.episodes}"
        holder.binding.tvRating.text = "Rating: ${animeItem.score}"
        Glide.with(mContext).load(animeItem.images.jpg.image_url).into(holder.binding.ivPoster)
        holder.binding.tvDuration.text = "Duration: ${animeItem.duration}"

        holder.binding.mainLayout.setOnClickListener {
            val intent = Intent(mContext, AnimeDetailsActivity::class.java)
            intent.putExtra("animeId", animeList.data[position].mal_id)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = animeList.data.size
}
