package com.example.seekhoassignment.ui.animeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoassignment.data.model.AnimeDetail
import com.example.seekhoassignment.data.repository.AnimeRepository
import com.example.seekhoassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    private val _animeDetails = MutableLiveData<Resource<AnimeDetail>>()
    val animeDetails: LiveData<Resource<AnimeDetail>> get() = _animeDetails

    fun fetchAnimeDetails(animeId: Int) {
        _animeDetails.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetails(animeId)
                if (response.isSuccessful && response.body() != null) {
                    _animeDetails.postValue(Resource.Success(response.body()!!))
                } else {
                    _animeDetails.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                _animeDetails.postValue(Resource.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
    }
}