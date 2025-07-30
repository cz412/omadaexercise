package com.example.omadaexercise.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThumbnailGrid(
    imageUrls: List<String>,
    columns: Int,
    onImageClick: (String) -> Unit)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize()
    ) {
        items(imageUrls) { url ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(120.dp)
                    .clickable { onImageClick(url) }
            ) {
                GlideImageLoader(
                    url = url,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}