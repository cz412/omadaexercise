package com.example.omadaexercise.model

class PhotoResponseModel (
    val photos: Photos
)

data class Photos(
    val photo: List<Photo>
)

data class Photo(
    val id: String,
    val farm: Int,
    val server: String,
    val secret: String,
    val title: String
)