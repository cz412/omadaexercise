package com.example.omadaexercise.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoTopAppBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {
                PhotoSearchBox(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                PhotoSearchButton(
                    onClick = onSearchClick,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun PhotoTopAppBarPreview() {
    MaterialTheme {
        PhotoTopAppBar(
            searchQuery = "Forest",
            onSearchQueryChange = {},
            onSearchClick = {}
        )
    }
}