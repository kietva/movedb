package com.example.movedb.screen.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieEntity
import com.example.movedb.BuildConfig
import com.example.movedb.R
import com.example.movedb.databinding.ItemMovieBinding

class MovieViewHolder(private val context: Context,private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(
        data: MovieEntity,
        onItemSelected: (MovieEntity) -> Unit
    ) {
        binding.tvTitle.text = data.title
        Glide.with(context)
            .load(BuildConfig.IMAGE_URL + data.posterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgPoster)
        binding.root.setOnClickListener {
            onItemSelected(data)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup): MovieViewHolder {
            val binding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MovieViewHolder(parent.context,binding)
        }
    }
}