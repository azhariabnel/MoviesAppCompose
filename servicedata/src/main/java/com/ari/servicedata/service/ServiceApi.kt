package com.ari.servicedata.service

import com.ari.servicedata.model.DetailMovieResponse
import com.ari.servicedata.model.GenreResponse
import com.ari.servicedata.model.MoviesByGenre
import com.ari.servicedata.model.NowPlayingResponse
import com.ari.servicedata.model.PopularResponse
import com.ari.servicedata.model.ReviewsResponse
import com.ari.servicedata.model.TopRatedResponse
import com.ari.servicedata.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {


    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String ): NowPlayingResponse

    @GET("movie/{path}")
    suspend fun getMovieDetail(@Path("path") movieId: String, @Query("api_key") apiKey: String): DetailMovieResponse

    @GET("movie/popular")
    suspend fun getPopular(@Query("api_key") apiKey: String ): PopularResponse

    @GET("movie/{path}/recommendations")
    suspend fun getRecommendation(@Path("path") movieId: String, @Query("api_key") apiKey: String): TopRatedResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenre(@Query("language") language: String,@Query("api_key") apiKey: String): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("page") page: Int,@Query("with_genres") withGenres: String,@Query("api_key") apiKey: String): MoviesByGenre
    @GET("movie/{path}/videos")
    suspend fun getMoviesTrailer(@Path("path") movieId: String, @Query("api_key") apiKey: String): TrailerResponse
    @GET("movie/{path}/reviews")
    suspend fun getMoviesReviews(@Path("path") movieId: String, @Query("api_key") apiKey: String): ReviewsResponse
}