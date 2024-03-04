package com.example.movedb.di

import com.example.data.api.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideApiService(retrofit = get()) }
}

internal fun provideApiService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)