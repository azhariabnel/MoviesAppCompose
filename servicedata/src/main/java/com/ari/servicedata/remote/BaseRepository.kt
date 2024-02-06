package com.ari.servicedata.remote

import com.ari.servicedata.Result
import com.ari.servicedata.model.DetailMovieResponse
import com.ari.servicedata.model.GenreResponse
import com.ari.servicedata.model.MoviesByGenre
import com.ari.servicedata.model.NowPlayingResponse
import com.ari.servicedata.model.PopularResponse
import com.ari.servicedata.model.ReviewsResponse
import com.ari.servicedata.model.TopRatedResponse
import com.ari.servicedata.model.TrailerResponse

interface BaseRepository {
    suspend fun getNowPlaying(apiKey: String) : Result<NowPlayingResponse>
    suspend fun getDetailMovie(movieId: String, apiKey: String) : Result<DetailMovieResponse>
    suspend fun getPopular(apiKey: String) : Result<PopularResponse>
    suspend fun getRecommendation(movieId: String, apiKey: String) : Result<TopRatedResponse>
    suspend fun getMoviesGenre(language: String, apiKey: String) : Result<GenreResponse>
    suspend fun getMoviesByGenre(page: Int,withGenres: String, apiKey: String) : Result<MoviesByGenre>
    suspend fun getMoviesTrailer(movieId: String,apiKey: String) : Result<TrailerResponse>
    suspend fun getMoviesReviews(movieId: String,apiKey: String) : Result<ReviewsResponse>
}