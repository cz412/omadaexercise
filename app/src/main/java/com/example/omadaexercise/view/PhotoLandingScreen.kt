package com.example.omadaexercise.view

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
import com.example.omadaexercise.toLargeImage

@Composable
fun PhotoLandingScreen(photoViewModel: PhotoViewModel) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedImageUrl = remember { mutableStateOf<String?>(null) }
    val photoUrlsState = photoViewModel.photoUrls.collectAsState().value
    //error snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    //pagination
    val gridSize = 15
    val currentPage = rememberSaveable { androidx.compose.runtime.mutableIntStateOf(0) }
    val images = photoUrlsState.success
    val totalPages = (images.size + gridSize - 1) / gridSize
    val startIndex = currentPage.intValue * gridSize
    val endIndex = minOf(startIndex + gridSize, images.size)
    val pageImages = if (images.isNotEmpty()) {
        images.subList(startIndex, endIndex).map { it.url }
    } else {
        emptyList()
    }
    //close keyboard when search is clicked
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            PhotoTopAppBar(
                isLoading = photoUrlsState.isLoading,
                searchQuery = searchQuery.value,
                onSearchQueryChange = { searchQuery.value = it },
                onSearchClick = {
                    photoViewModel.loadPhotos(searchQuery.value)
                    focusManager.clearFocus()
                    currentPage.intValue = 0
                }
            )
        },
        bottomBar = {
            if (images.size > gridSize) {
                PhotoPagination(
                    currentPage = currentPage.intValue,
                    totalPages = totalPages,
                    onPageSelected = { page -> currentPage.intValue = page },
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
                        PhotoScreen(
                            images = pageImages,
                            onImageClick = { photoInfo -> selectedImageUrl.value = photoInfo },
                            columns = 3
                        )
                    }
                }
            }
        }
        if (selectedImageUrl.value != null) {
            PhotoDialog(
                imageUrl = selectedImageUrl.value.toLargeImage(),
                onDismiss = { selectedImageUrl.value = null }
            )
        }
    }
}