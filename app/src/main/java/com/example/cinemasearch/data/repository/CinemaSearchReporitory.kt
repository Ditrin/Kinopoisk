package com.example.cinemasearch.data.repository

import android.util.Log
import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.remote.Networking

class CinemaSearchReporitory() {

    suspend fun getListMovies(): List<Film>{
        Log.e("Main", "size.toString()")

        val value = Networking.cinemaSearchAPI.getAllMovies().films
        Log.e("Main", value.size.toString())
        return value
    }

    suspend fun getSearchCharacter(name: String): List<Film>{
        return  Networking.cinemaSearchAPI.getSearchFilm(name).films
    }

}