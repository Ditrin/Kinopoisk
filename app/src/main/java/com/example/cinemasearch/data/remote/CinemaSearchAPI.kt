package com.example.cinemasearch.data.remote

import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.DTOmodel.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface CinemaSearchAPI {
    @GET("/api/v2.2/films/top")
    suspend fun getAllMovies(): Movies

    @GET("/v2.1/films/search-by-keyword")
    suspend fun getSearchFilm(
        @Query("name") query: String
    ) : Movies

}