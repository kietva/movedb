package com.example.movedb.screen.movie

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.MovieEntity
import com.example.domain.usercase.GetPopularMovies
import com.example.movedb.core.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class MovieViewModel(private val getPopularMovies : GetPopularMovies) : BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        MovieUiState.Error(exception.message)
    }

    private val _uiState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun fetchPopularMovies(){
        launchCoroutineIO {
            getPopularMovies
                .invoke()
                .cachedIn(viewModelScope)
                .catch { throwable->
                    _uiState.value = MovieUiState.Error(throwable.message)
                }
                .collectLatest { pagingData ->
                    _uiState.value = MovieUiState.Complete(pagingData)
                }
        }
    }

    sealed class MovieUiState{
        object Initial : MovieUiState()
        data class Complete(var movies : PagingData<MovieEntity>) : MovieUiState()
        data class Error(var message : String?) : MovieUiState()
    }
}