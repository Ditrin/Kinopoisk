package com.example.cinemasearch.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemasearch.data.model.Film
import com.example.cinemasearch.data.repository.CinemaSearchRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel:ViewModel() {
    private val listMoviesLiveData = MutableLiveData<List<Film>>()
    val listMovie: LiveData<List<Film>> = listMoviesLiveData
    private var job: Job? = null
    private val repository = CinemaSearchRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val isErrorLiveData = MutableLiveData(true)
    val isError: LiveData<Boolean> = isErrorLiveData

    init {
        getMoviesList()
    }

    fun onRefreshList() {
        listMoviesLiveData.postValue(emptyList())
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getListMovies()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listMoviesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)

            }
        }
    }

    private fun getMoviesList() {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getListMovies()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listMoviesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                isErrorLiveData.value = true

            }
        }
    }

    fun onSearchClicked(keyword: String) {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                if (keyword.isBlank()) {
                    repository.getListMovies()
                } else {
                    repository.getSearchMovie(keyword)
                }
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listMoviesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}