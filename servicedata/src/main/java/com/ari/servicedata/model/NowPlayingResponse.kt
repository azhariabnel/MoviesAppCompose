package com.ari.servicedata.model

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(

	val dates: Dates? = null,
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	val totalResults: Int? = null
)

data class ResultsItem(

	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,
	
	val backdropPath: String? = null,
	val releaseDate: String? = null,
	val popularity: Any? = null,
	val voteAverage: Any? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
)

data class Dates(

	val maximum: String? = null,
	val minimum: String? = null
)
