package com.example.moviesappcompose.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.servicedata.Result
import com.ari.servicedata.model.*
import com.ari.servicedata.remote.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class NowPlayingUiState<T>(
    val nowPlaying: T? = null,
    val success: Boolean = false,
    val error: Exception? = null
)

data class DetailMovieUiState<T>(
    val detailMovie: T? = null,
    val success: Boolean = false,
    val error: Exception? = null
)

data class PopularMovieUiState<T>(
    val popular: T? = null,
    val success: Boolean = false,
    val error: Exception? = null
)

data class RecommendationMovieUiState<T>(
    val recommendation: T? = null,
    val success: Boolean = false,
    val error: Exception? = null
)

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _nowPlayingUiState = MutableStateFlow(NowPlayingUiState<NowPlayingResponse>())
    val nowPlayingUiState: StateFlow<NowPlayingUiState<NowPlayingResponse>> = _nowPlayingUiState.asStateFlow()

    private val _detailMovieUiState = MutableStateFlow(DetailMovieUiState<DetailMovieResponse>())
    val detailMovieUiState: StateFlow<DetailMovieUiState<DetailMovieResponse>> = _detailMovieUiState.asStateFlow()

    private val _popularUiState = MutableStateFlow(PopularMovieUiState<PopularResponse>())
    val popularUiState: StateFlow<PopularMovieUiState<PopularResponse>> = _popularUiState.asStateFlow()

    private val _recommendationUiState = MutableStateFlow(RecommendationMovieUiState<TopRatedResponse>())
    val recommendationUiState: StateFlow<RecommendationMovieUiState<TopRatedResponse>> = _recommendationUiState.asStateFlow()

    var detailMovieData by mutableStateOf<String?>(null)
        private set

    fun getNowPlaying(){
        viewModelScope.launch {
            val result = repository.getNowPlaying(apiKey = "fbb9572d11b5458ac98f02b84f2bafc4")
            _nowPlayingUiState.update {
                when(result){
                    is Result.Success -> it.copy(
                        nowPlaying = result.data,success = true,error = null)
                    is Result.Error -> it.copy(error = result.exception, success = false)
                }
            }
        }
    }

    fun getMovieDetail(movieId: String){
        viewModelScope.launch {
            val result = repository.getDetailMovie(movieId,apiKey = "fbb9572d11b5458ac98f02b84f2bafc4")
            _detailMovieUiState.update {
                when(result){
                    is Result.Success -> it.copy(
                        detailMovie = result.data,success = true,error = null)
                    is Result.Error -> it.copy(error = result.exception, success = false)
                }
            }
        }
    }

    fun getPopular(){
        viewModelScope.launch {
            val result = repository.getPopular(apiKey = "fbb9572d11b5458ac98f02b84f2bafc4")
            _popularUiState.update {
                when(result){
                    is Result.Success -> it.copy(
                        popular = result.data,success = true,error = null)
                    is Result.Error -> it.copy(error = result.exception, success = false)
                }
            }
        }
    }

    fun getRecommendation(movieId: String){
        viewModelScope.launch {
            val result = repository.getRecommendation(movieId,apiKey = "fbb9572d11b5458ac98f02b84f2bafc4")
            _recommendationUiState.update {
                when(result){
                    is Result.Success -> it.copy(
                        recommendation = result.data,success = true,error = null)
                    is Result.Error -> it.copy(error = result.exception, success = false)
                }
            }
        }
    }

    fun addDetailData(movieId: String){
        detailMovieData = movieId
    }

}