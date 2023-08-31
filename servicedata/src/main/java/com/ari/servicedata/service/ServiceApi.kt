package com.ari.servicedata.service

import com.ari.servicedata.model.DetailMovieResponse
import com.ari.servicedata.model.NowPlayingResponse
import com.ari.servicedata.model.PopularResponse
import com.ari.servicedata.model.TopRatedResponse
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
}