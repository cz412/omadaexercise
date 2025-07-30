package com.example.omadaexercise.view

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.omadaexercise.R

@Composable
fun PhotoSearchBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.search_photos)) },
        modifier = modifier,
        singleLine = true,
        maxLines = 1
    )
}

@Preview
@Composable
fun PhotoSearchBoxPreview() {
    PhotoSearchBox(
        value = "Rivers",
        onValueChange = {}
    )
}