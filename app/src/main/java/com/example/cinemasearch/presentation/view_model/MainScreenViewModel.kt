package com.example.cinemasearch.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.repository.CinemaSearchReporitory
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel:ViewModel() {

    private val listMoviesLiveData = MutableLiveData<List<Film>>()
    val listMovie: LiveData<List<Film>> = listMoviesLiveData
    private var job: Job? = null
    private val repository = CinemaSearchReporitory()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData
    private val searchTextLiveData = MutableLiveData<String>()
    val searchText: LiveData<String> = searchTextLiveData

    fun saveText(text: String) {
        searchTextLiveData.postValue("")
        searchTextLiveData.postValue(text)
    }

    fun refreshList() {
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
                val tmp = it
            }
        }
    }

    fun getMoviesList() {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getListMovies()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listMoviesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }

    fun getSearchList(keyword: String) {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getSearchMovie(keyword)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listMoviesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }
}