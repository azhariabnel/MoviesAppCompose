package com.ari.servicedata.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(

	@field:SerializedName("genres")
	val genres: List<MoviesGenreItem?>? = null
)

data class MoviesGenreItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
