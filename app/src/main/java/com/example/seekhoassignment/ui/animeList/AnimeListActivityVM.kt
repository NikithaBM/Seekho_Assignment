package com.example.seekhoassignment.ui.animeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoassignment.data.model.AnimePOJO
import com.example.seekhoassignment.data.repository.AnimeRepository
import com.example.seekhoassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeListViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    private val _animeList = MutableLiveData<Resource<AnimePOJO>>()
    val animeList: LiveData<Resource<AnimePOJO>> get() = _animeList

    init{
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        _animeList.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val response = repository.getTopAnime()
                if (response.isSuccessful && response.body() != null) {
                    _animeList.postValue(Resource.Success(response.body()!!))
                } else {
                    _animeList.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                _animeList.postValue(Resource.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
    }
}