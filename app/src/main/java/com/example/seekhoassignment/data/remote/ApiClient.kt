package com.example.seekhoassignment.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.seekhoassignment.BuildConfig
import com.example.seekhoassignment.data.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Provides
    @Singleton
    fun getRetrofitClient(baseUri : String): Retrofit {
        val retrofit: Retrofit?
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
            .connectTimeout(50000, TimeUnit.SECONDS)
            .readTimeout(50000, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUri)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideBaseUri(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideAnimeApi(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideAnimeRepository(api: NetworkService): AnimeRepository = AnimeRepository(api)
}