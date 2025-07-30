package com.example.omadaexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.omadaexercise.ui.theme.OmadaexerciseTheme
import com.example.omadaexercise.view.PhotoLandingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val photoViewModel: PhotoViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            OmadaexerciseTheme {
                PhotoLandingScreen(photoViewModel = photoViewModel)
            }
        }
    }
}