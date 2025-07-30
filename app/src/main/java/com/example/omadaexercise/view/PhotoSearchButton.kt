package com.example.omadaexercise.view

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PhotoSearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val photoButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = photoButtonColors
    ) {
        Text("Search")
    }
}



@Preview
@Composable
fun PreviewPhotoButton() {
    PhotoSearchButton(
        onClick = {}
    )
}