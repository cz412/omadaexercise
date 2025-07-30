package com.example.omadaexercise.repo

import com.example.omadaexercise.model.PhotoResponseModel
import com.example.omadaexercise.network.ApiService
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiKey: String
) {
    suspend fun getPhotos(query: String): PhotoResponseModel {
        return if (query.isBlank()) {
            apiService.getRecentPhotos(apiKey)
        } else {
            apiService.searchPhotos(apiKey, query)
        }
    }
}