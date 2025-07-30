package com.example.omadaexercise.network

import com.example.omadaexercise.model.PhotoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    suspend fun getRecentPhotos(@Query("api_key") apiKey: String): PhotoResponseModel

    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("api_key") apiKey: String,
        @Query("text") text: String
    ): PhotoResponseModel
}