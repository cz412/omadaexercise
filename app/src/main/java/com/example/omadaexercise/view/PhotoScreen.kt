package com.example.omadaexercise.view

import androidx.compose.runtime.Composable

@Composable
fun PhotoScreen(
    images: List<String>,
    onImageClick: (String) -> Unit,
    columns: Int
) {
    ThumbnailGrid(
        imageUrls = images,
        onImageClick = onImageClick,
        columns = columns
    )
}