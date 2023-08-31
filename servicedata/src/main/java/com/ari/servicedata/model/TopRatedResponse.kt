package com.ari.servicedata.model

import com.google.gson.annotations.SerializedName


data class TopRatedResponse(

	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsTopRatedItem?>? = null,
	val totalResults: Int? = null
)

data class ResultsTopRatedItem(

	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	val backdropPath: String? = null,
	val mediaType: String? = null,
	val releaseDate: String? = null,
	val popularity: Any? = null,
	val voteAverage: Any? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
)
