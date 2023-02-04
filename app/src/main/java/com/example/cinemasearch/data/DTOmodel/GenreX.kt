package com.example.cinemasearch.data.DTOmodel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class GenreX(
    val genre: String
)