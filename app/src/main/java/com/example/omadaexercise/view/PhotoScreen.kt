package com.example.omadaexercise.view

import androidx.compose.runtime.Composable
import com.example.omadaexercise.PhotosInfo

@Composable
fun PhotoScreen(
    images: List<PhotosInfo>,
    onImageClick: (PhotosInfo) -> Unit,
    columns: Int
) {
    ThumbnailGrid(
        photos = images,
        onImageClick = onImageClick,
        columns = columns
    )
}