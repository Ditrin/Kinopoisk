package com.example.cinemasearch.data.DTOmodel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FilmById(
    val nameRu: String?,
    val countries: List<CountryX>,
    val description: String?,
    val genres: List<GenreX>,
    val posterUrl: String,
    val posterUrlPreview: String
)