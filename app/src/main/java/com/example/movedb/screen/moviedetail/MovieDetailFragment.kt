package com.example.movedb.screen.moviedetail

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieEntity
import com.example.movedb.BuildConfig
import com.example.movedb.core.base.BaseFragment
import com.example.movedb.core.base.EmptyViewModel
import com.example.movedb.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding,EmptyViewModel>() {
    override val viewModel: EmptyViewModel by viewModel<EmptyViewModel>()

    override fun getViewBinding(): FragmentMovieDetailBinding {
       return FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieEntity>(MOVIE_DATA)?.let {
            Glide.with(this).load(BuildConfig.IMAGE_URL + it.backdropPath).into(binding.imgBackdrop)
            Glide.with(this).load(BuildConfig.IMAGE_URL + it.posterPath).into(binding.imgPoster)
            binding.tvTitle.text = it.title
            binding.tvOverview.text = it.overview
        }
    }

    override fun observerEvents() {
    }

    companion object{
        const val MOVIE_DATA = "MOVIE_DATA"
    }
}