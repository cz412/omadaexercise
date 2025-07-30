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
    bigImage: Boolean = false,
    contentDescription: String?
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                if (bigImage) {
                    this.scaleType = ImageView.ScaleType.FIT_CENTER
                } else {
                    this.scaleType = ImageView.ScaleType.CENTER_CROP
                }
                this.contentDescription = contentDescription
            }
        },
        update = { imageView ->
            Glide.with(imageView.context)
                .load(url)
                .error(android.R.drawable.ic_dialog_alert)
                .into(imageView)
        }
    )
}