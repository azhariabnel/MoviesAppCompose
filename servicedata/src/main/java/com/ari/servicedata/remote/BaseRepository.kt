package com.ari.servicedata.remote

import com.ari.servicedata.Result
import com.ari.servicedata.ResultList
import com.ari.servicedata.model.*

interface BaseRepository {
    suspend fun getNowPlaying(apiKey: String) : Result<NowPlayingResponse>
    suspend fun getDetailMovie(movieId: String, apiKey: String) : Result<DetailMovieResponse>
    suspend fun getPopular(apiKey: String) : Result<PopularResponse>
    suspend fun getRecommendation(movieId: String, apiKey: String) : Result<TopRatedResponse>
}