package com.example.omadaexercise.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.omadaexercise.PhotosInfo

@Composable
fun ThumbnailGrid(
    photos: List<PhotosInfo>,
    onImageClick: (PhotosInfo) -> Unit,
    columns: Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos) { photo ->
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clickable { onImageClick(photo) }
            ) {
                GlideImageLoader(
                    url = photo.url,
                    contentDescription = photo.title,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}