package com.example.omadaexercise

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omadaexercise.repo.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _photoUrls = MutableStateFlow(PhotoUrlsDataState())
    val photoUrls: StateFlow<PhotoUrlsDataState> = _photoUrls

    fun loadPhotos(query: String) {
        viewModelScope.launch {
            _photoUrls.value = PhotoUrlsDataState(isLoading = true)
            try {
                val response = repository.getPhotos(query)
                val urls = response.photos.photo.map { photo ->
                    PhotosInfo(
                        id = photo.id,
                        title = photo.title,
                        url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_s.jpg"
                    )
                }
                _photoUrls.value = PhotoUrlsDataState(success = urls)
            } catch (e: Exception) {
                _photoUrls.value = PhotoUrlsDataState(error = e.message ?: "Unknown error")
            }
        }
    }
}

@Immutable
data class PhotoUrlsDataState(
    val isLoading: Boolean = false,
    val success: List<PhotosInfo> = emptyList(),
    val error: String? = null
)

@Immutable
data class PhotosInfo(
    val id: String,
    val title: String,
    val url: String
)