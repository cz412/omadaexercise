package com.example.omadaexercise.view

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.omadaexercise.R


@Composable
fun PhotoSearchButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val photoButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = photoButtonColors,
        enabled = enabled
    ) {
        Text(stringResource(R.string.search))
    }
}



@Preview
@Composable
fun PreviewPhotoButton() {
    PhotoSearchButton(
        onClick = {}
    )
}