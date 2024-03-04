package com.example.movedb.di

import com.example.domain.usercase.GetPopularMovies
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetPopularMovies(
            movieRepository = get(),
        )
    }
}