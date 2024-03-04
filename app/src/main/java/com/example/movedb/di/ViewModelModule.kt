package com.example.movedb.di

import com.example.movedb.core.base.EmptyViewModel
import com.example.movedb.screen.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MovieViewModel(getPopularMovies = get())
    }
    viewModel {
        EmptyViewModel()
    }
}