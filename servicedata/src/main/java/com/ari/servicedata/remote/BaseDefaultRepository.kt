package com.ari.servicedata.remote

import android.content.Context
import com.ari.servicedata.Result
import com.ari.servicedata.di.AppModule
import com.ari.servicedata.model.DetailMovieResponse
import com.ari.servicedata.model.GenreResponse
import com.ari.servicedata.model.MoviesByGenre
import com.ari.servicedata.model.NowPlayingResponse
import com.ari.servicedata.model.PopularResponse
import com.ari.servicedata.model.ReviewsResponse
import com.ari.servicedata.model.TopRatedResponse
import com.ari.servicedata.model.TrailerResponse
import com.ari.servicedata.service.ServiceApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

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

    override suspend fun getMoviesGenre(language: String, apiKey: String): Result<GenreResponse> {
        return try {
            val result = api.getMovieGenre(language,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getMoviesByGenre(
        page: Int,
        withGenres: String,
        apiKey: String
    ): Result<MoviesByGenre> {
        return try {
            val result = api.getMoviesByGenre(page,withGenres,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getMoviesTrailer(
        movieId: String,
        apiKey: String
    ): Result<TrailerResponse> {
        return try {
            val result = api.getMoviesTrailer(movieId,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }

    override suspend fun getMoviesReviews(
        movieId: String,
        apiKey: String
    ): Result<ReviewsResponse> {
        return try {
            val result = api.getMoviesReviews(movieId,apiKey)
            Result.Success(result)
        } catch (ex : Exception){
            Result.Error(ex)
        }
    }


}