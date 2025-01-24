package com.example.seekhoassignment.data.remote

import com.example.seekhoassignment.data.model.AnimeDetail
import com.example.seekhoassignment.data.model.AnimePOJO
import com.example.seekhoassignment.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("/v4/top/anime")
    suspend fun fetchTopAnimeRecords() : Response<AnimePOJO>

    @GET("/v4/anime/{anime_id}")
    suspend fun fetchAnimeRecordById(@Path("anime_id") animeId: Int) : Response<AnimeDetail>

}