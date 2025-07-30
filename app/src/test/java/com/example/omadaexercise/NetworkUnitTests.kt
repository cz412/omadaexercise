package com.example.omadaexercise

import com.example.omadaexercise.model.Photo
import com.example.omadaexercise.model.Photos
import com.example.omadaexercise.model.PhotoResponseModel
import com.example.omadaexercise.network.ApiService
import com.example.omadaexercise.repo.PhotoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkUnitTests {

    private lateinit var apiService: ApiService
    private lateinit var repository: PhotoRepository

    @Before
    fun setup() {
        apiService = mockk()
        repository = PhotoRepository(apiService, "test_api_key")
    }

    @Test
    fun `getPhotos returns recent photos when query is blank`() = runTest {
        val expectedResponse = PhotoResponseModel(
            photos = Photos(
                photo = listOf(
                    Photo("1", 1, "server", "secret", "title")
                )
            )
        )
        coEvery { apiService.getRecentPhotos("test_api_key") } returns expectedResponse

        val result = repository.getPhotos("")
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.getRecentPhotos("test_api_key") }
        coVerify(exactly = 0) { apiService.searchPhotos(any(), any()) }
    }

    @Test
    fun `getPhotos calls searchPhotos when query is not blank`() = runTest {
        val expectedResponse = PhotoResponseModel(
            photos = Photos(
                photo = listOf(
                    Photo("2", 2, "server2", "secret2", "title2")
                )
            )
        )
        coEvery { apiService.searchPhotos("test_api_key", "cat") } returns expectedResponse

        val result = repository.getPhotos("cat")
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.searchPhotos("test_api_key", "cat") }
        coVerify(exactly = 0) { apiService.getRecentPhotos(any()) }
    }
}