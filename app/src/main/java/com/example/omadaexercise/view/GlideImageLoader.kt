package com.example.omadaexercise.view

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GlideImageLoader(
    modifier: Modifier = Modifier,
    url: String?,
    contentDescription: String?
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                Glide.with(context)
                    .load(url)
                    .error(android.R.drawable.ic_dialog_alert)
                    .into(this)
                this.contentDescription = contentDescription
                this.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    )
}