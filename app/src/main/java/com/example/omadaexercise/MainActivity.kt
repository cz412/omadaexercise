package com.example.omadaexercise

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.omadaexercise.ui.theme.OmadaexerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val photoViewModel: PhotoViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            OmadaexerciseTheme {
                photoViewModel.loadPhotos("")
                LaunchedEffect(Unit) {
                    photoViewModel.photoUrls.value.let { state ->
                        Log.d("API_TEST", "Loading: ${state.isLoading}, Success: ${state.success}, Error: ${state.error}")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OmadaexerciseTheme {
        Greeting("Android")
    }
}