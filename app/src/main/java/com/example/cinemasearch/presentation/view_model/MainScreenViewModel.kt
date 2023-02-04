package com.example.cinemasearch.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.data.repository.CinemaSearchReporitory
import com.example.cinemasearch.presentation.adapters.CinemaSearchAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel:ViewModel() {

    private val listCharacterLiveData = MutableLiveData<List<Film>>()
    val listCharacter: LiveData<List<Film>> = listCharacterLiveData
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
        listCharacterLiveData.postValue(emptyList())
        job?.cancel()
        job = GlobalScope.launch {
            kotlin.runCatching {
                repository.getListMovies()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listCharacterLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }

    fun getCharacterList() {
        job?.cancel()
        Log.e("Main", "smth")

        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getListMovies()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listCharacterLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }

    fun getSearchList(name: String) {
        job?.cancel()
        job = GlobalScope.launch {//не работает с viewModelScope, зато работает с Global, магия)))
            kotlin.runCatching {
                repository.getSearchCharacter(name)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listCharacterLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }
}