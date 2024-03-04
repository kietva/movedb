package com.example.domain.usercase

import androidx.paging.PagingData
import com.example.domain.entity.MovieEntity
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<PagingData<MovieEntity>> = movieRepository.popularMovies()
}