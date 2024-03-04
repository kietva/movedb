package com.example.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.MovieService
import com.example.data.models.toEntity
import com.example.domain.entity.MovieEntity

class MoviePagingSource(
    private val movieApi: MovieService
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = movieApi.getPopularMovies(nextPageNumber)
            val entityResults = response.results.map { it.toEntity() }
            LoadResult.Page(
                data = entityResults,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (entityResults.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}