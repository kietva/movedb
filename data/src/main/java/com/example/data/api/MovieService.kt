package com.example.data.api

import com.example.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular?language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieResponse
}