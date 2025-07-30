package com.example.omadaexercise

fun String?.toLargeImage(): String {
    return if (this != null && this.endsWith("_s.jpg")) {
        this.replace("_s.jpg", "_b.jpg")
    } else {
        this ?: ""
    }
}