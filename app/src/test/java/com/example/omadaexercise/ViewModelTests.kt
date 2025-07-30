package com.example.omadaexercise

import com.example.omadaexercise.model.Photo
import com.example.omadaexercise.model.PhotoResponseModel
import com.example.omadaexercise.model.Photos
import com.example.omadaexercise.repo.PhotoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTests {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: PhotoViewModel
    private lateinit var repository: PhotoRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = PhotoViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadPhotos emits loading and success state`() = runTest {
        val mockPhotos = listOf(
            PhotosInfo("1", "title1", "url1"),
            PhotosInfo("2", "title2", "url2")
        )
        val response = PhotoResponseModel(photos = Photos(photo = mockPhotos.map {
            Photo(it.id, 1, "server1", "secret1", it.title)
        }))

        coEvery { repository.getPhotos("cats") } returns response

        viewModel.loadPhotos("cats")
        advanceUntilIdle()

        val state = viewModel.photoUrls.value
        assertFalse(state.isLoading)
        assertEquals(2, state.success.size)
        assertNull(state.error)
    }

    @Test
    fun `loadPhotos emits loading and error state on failure`() = runTest {
        coEvery { repository.getPhotos("fail") } throws Exception("Network error")

        viewModel.loadPhotos("fail")
        advanceUntilIdle()

        val state = viewModel.photoUrls.value
        assertFalse(state.isLoading)
        assertTrue(state.success.isEmpty())
        assertEquals("Network error", state.error)
    }
}
