package com.example.movedb.screen.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movedb.R
import com.example.movedb.core.base.BaseFragment
import com.example.movedb.core.dialog.dismissLoadingDialog
import com.example.movedb.core.dialog.showLoadingDialog
import com.example.movedb.databinding.FragmentMovieBinding
import com.example.movedb.screen.movie.adapter.MovieAdapter
import com.example.movedb.screen.moviedetail.MovieDetailFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding,MovieViewModel>() {
    override val viewModel: MovieViewModel by viewModel<MovieViewModel>()

    private val movieAdapter by lazy {
        MovieAdapter { movieEntity ->
            findNavController().navigate(
                R.id.movieDetailFragment, bundleOf(MovieDetailFragment.MOVIE_DATA to movieEntity)
            )
        }
    }

    override fun getViewBinding(): FragmentMovieBinding {
       return FragmentMovieBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchPopularMovies()
    }

    override fun observerEvents() {
        @Suppress("DEPRECATION")
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.uiState.collectLatest { movieState->
                    when (movieState) {
                        MovieViewModel.MovieUiState.Initial -> showLoadingDialog()
                        is MovieViewModel.MovieUiState.Error -> {
                            dismissLoadingDialog()
                            Toast.makeText(context, movieState.message, Toast.LENGTH_SHORT).show()
                        }
                        is MovieViewModel.MovieUiState.Complete -> {
                            dismissLoadingDialog()
                            movieAdapter.submitData(movieState.movies)
                        }
                    }
                }
            }

            movieAdapter.loadStateFlow.collectLatest{
                when (val currentState = it.source.refresh) {
                    is LoadState.Loading -> {
                        showLoadingDialog()
                    }
                    is LoadState.Error -> {
                        dismissLoadingDialog()
                        Toast.makeText(context, currentState.error.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> { dismissLoadingDialog() }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(false)
        }
    }
}