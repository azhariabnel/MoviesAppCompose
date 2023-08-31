package com.ari.servicedata.remote

import android.content.Context
import com.ari.servicedata.Result
import com.ari.servicedata.Utilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.ari.servicedata.di.AppModule
import com.ari.servicedata.model.*
import com.ari.servicedata.service.ServiceApi
import java.lang.Exception

class BaseDefaultRepository @Inject constructor(
    @ApplicationContext val context: Context,
    @AppModule.ServiceApiRuntime val api: ServiceApi
) : BaseRepository {

    override suspend fun getNowPlaying(apiKey: String): Result<NowPlayingResponse> {
       return try {
            val result = api.getNowPlaying(apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getDetailMovie(
        movieId: String,
        apiKey: String
    ): Result<DetailMovieResponse> {
        return try {
            val result = api.getMovieDetail(movieId,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getPopular(apiKey: String): Result<PopularResponse> {
        return try {
            val result = api.getPopular(apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getRecommendation(
        movieId: String,
        apiKey: String
    ): Result<TopRatedResponse> {
        return try {
            val result = api.getRecommendation(movieId,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }


}