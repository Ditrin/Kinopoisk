package com.example.cinemasearch.data.repository

import com.example.cinemasearch.data.model.Film
import com.example.cinemasearch.data.model.FilmById
import com.example.cinemasearch.data.remote.Networking

class CinemaSearchRepository {

    suspend fun getListMovies(): List<Film> {
        return Networking.cinemaSearchAPI.getAllMovies().films
    }

    suspend fun getSearchMovie(keyword: String): List<Film>{
        return  Networking.cinemaSearchAPI.getSearchFilm(keyword).films
    }

    suspend fun getSingleMovie(id: Int): FilmById{
        return Networking.cinemaSearchAPI.getSingleMovie(id)
    }

}