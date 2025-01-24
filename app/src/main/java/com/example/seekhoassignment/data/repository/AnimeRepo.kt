package com.example.seekhoassignment.data.repository

import com.example.seekhoassignment.BuildConfig
import com.example.seekhoassignment.data.remote.ApiClient
import com.example.seekhoassignment.data.remote.NetworkService
import javax.inject.Inject


class AnimeRepository @Inject constructor(private val api: NetworkService) {

    //private val api: NetworkService = ApiClient.getRetrofitClient(BuildConfig.BASE_URL)
        //.create(NetworkService::class.java)
        suspend fun getTopAnime() = api.fetchTopAnimeRecords()

        suspend fun getAnimeDetails(animeId: Int) = api.fetchAnimeRecordById(animeId)
    }
