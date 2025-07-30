package com.example.omadaexercise.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.omadaexercise.PhotoViewModel

@Composable
fun PhotoLandingScreen(photoViewModel: PhotoViewModel) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedImageUrl = remember { mutableStateOf<String?>(null) }
    val photoUrlsState = photoViewModel.photoUrls.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val gridSize = 15
    val focusManager = LocalFocusManager.current
    val currentPage = rememberSaveable { mutableStateOf(0) }
    val images = photoUrlsState.success

    val totalPages = (images.size + gridSize - 1) / gridSize
    val startIndex = currentPage.value * gridSize
    val endIndex = minOf(startIndex + gridSize, images.size)
    val pageImages = if (images.isNotEmpty()) {
        images.subList(startIndex, endIndex).map { it.url }
    } else {
        emptyList()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            PhotoTopAppBar(
                searchQuery = searchQuery.value,
                onSearchQueryChange = { searchQuery.value = it },
                onSearchClick = {
                    photoViewModel.loadPhotos(searchQuery.value)
                    focusManager.clearFocus()
                    currentPage.value = 0
                }
            )
        },
        bottomBar = {
            if (images.size > gridSize) {
                PhotoPagination(
                    currentPage = currentPage.value,
                    totalPages = totalPages,
                    onPageSelected = { page -> currentPage.value = page },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            ) {
                LaunchedEffect(photoUrlsState.error) {
                    photoUrlsState.error?.let { message ->
                        snackbarHostState.showSnackbar(message = message)
                    }
                }
                when {
                    photoUrlsState.isLoading -> {
                        LoadingScreen()
                    }
                    images.isNotEmpty() -> {
                        Log.d("PhotoLandingScreen", "Images received: ${images.size}")
                        PhotoScreen(
                            images = pageImages,
                            onImageClick = { photoInfo -> selectedImageUrl.value = photoInfo },
                            columns = 3
                        )
                    }
                    photoUrlsState.error != null -> {
                        ErrorScreen(message = photoUrlsState.error)
                    }
                }

            }
        }
    }
}