package com.example.cinemasearch.data.remote

import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.DTOmodel.FilmById
import com.example.cinemasearch.data.DTOmodel.Movies
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemaSearchAPI {
    @GET("/api/v2.2/films/top")
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getAllMovies(@Query("type")type: String = "TOP_100_POPULAR_FILMS"): Movies

    @GET("/api/v2.1/films/search-by-keyword")
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getSearchFilm(
        @Query("keyword") query: String
    ) : Movies

    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getSingleMovie(
        @Path("id") id: Int
    ): FilmById
}