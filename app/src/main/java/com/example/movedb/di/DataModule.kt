package com.example.movedb.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}