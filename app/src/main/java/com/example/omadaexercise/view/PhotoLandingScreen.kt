package com.example.omadaexercise.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.omadaexercise.PhotoViewModel

@Composable
fun PhotoLandingScreen(photoViewModel: PhotoViewModel) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedImageUrl = remember { mutableStateOf<String?>(null) }
    val photoUrlsState = photoViewModel.photoUrls.value
    val snackbarHostState = remember { SnackbarHostState() }
    val gridSize = 15
    val focusManager = LocalFocusManager.current

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
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                LaunchedEffect(photoUrlsState.error) {
                    photoUrlsState.error?.let { message ->
                        snackbarHostState.showSnackbar(message)
                    }
                }

                when {
                    photoUrlsState.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.DarkGray
                            )
                        }
                    }
                    photoUrlsState.success.isNotEmpty() -> {
                        val images = photoUrlsState.success.take(gridSize).map { it.url }
                        ThumbnailGrid(
                            imageUrls = images,
                            onImageClick = { url -> selectedImageUrl.value = url },
                            columns = 3
                        )
                    }
                    photoUrlsState.error != null -> {
                        Text(
                            text = photoUrlsState.error,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                if (selectedImageUrl.value != null) {
                    Dialog(onDismissRequest = { selectedImageUrl.value = null }) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GlideImageLoader(
                                url = selectedImageUrl.value ?: "",
                                contentDescription = "Full Image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}