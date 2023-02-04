package com.example.cinemasearch.data.DTOmodel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Film(
    val countries: List<Country>,
    val filmId: Int,
    val genres: List<Genre>,
    val nameEn: String?,
    val nameRu: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String?
)