package com.example.omadaexercise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PhotoPagination(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
) {
    val paginationButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White,
        disabledContentColor = Color(0xFFFF9800),
        disabledContainerColor = Color.DarkGray
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onPageSelected(currentPage - 1) },
            enabled = currentPage > 0,
            colors = paginationButtonColors
        ) {
            Text("Back")
        }
        Text("Page ${currentPage + 1} of $totalPages")
        Button(
            onClick = { onPageSelected(currentPage + 1) },
            enabled = currentPage < totalPages - 1,
            colors = paginationButtonColors
        ) {
            Text("Next")
        }
    }
}


@Preview
@Composable
fun PhotoPaginationPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            PhotoPagination(
                currentPage = 2,
                totalPages = 15,
                onPageSelected = {}
            )
        }
    }
}
