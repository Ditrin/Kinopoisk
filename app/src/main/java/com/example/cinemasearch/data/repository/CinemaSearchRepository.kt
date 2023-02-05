package com.example.cinemasearch.data.repository

import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.DTOmodel.FilmById
import com.example.cinemasearch.data.remote.Networking

class CinemaSearchRepository {

    suspend fun getListMovies(): List<Film>{
        val value = Networking.cinemaSearchAPI.getAllMovies().films
        return value
    }

    suspend fun getSearchMovie(keyword: String): List<Film>{
        return  Networking.cinemaSearchAPI.getSearchFilm(keyword).films
    }

    suspend fun getSingleMovie(id: Int): FilmById{
        return Networking.cinemaSearchAPI.getSingleMovie(id)
    }

}