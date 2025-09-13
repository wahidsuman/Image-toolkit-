package com.imagetoolkit.app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.imagetoolkit.app.R

data class ImageFeature(
    val id: String,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val icon: Int,
    val isAvailable: Boolean = false
)

object ImageFeatures {
    val features = listOf(
        ImageFeature(
            id = "background_removal",
            title = R.string.background_removal,
            description = R.string.background_removal_desc,
            icon = R.drawable.ic_background_removal,
            isAvailable = false
        ),
        ImageFeature(
            id = "filters_effects",
            title = R.string.filters_effects,
            description = R.string.filters_effects_desc,
            icon = R.drawable.ic_filters,
            isAvailable = false
        ),
        ImageFeature(
            id = "crop_resize",
            title = R.string.crop_resize,
            description = R.string.crop_resize_desc,
            icon = R.drawable.ic_crop,
            isAvailable = true
        ),
        ImageFeature(
            id = "format_conversion",
            title = R.string.format_conversion,
            description = R.string.format_conversion_desc,
            icon = R.drawable.ic_format,
            isAvailable = false
        ),
        ImageFeature(
            id = "rotate_flip",
            title = R.string.rotate_flip,
            description = R.string.rotate_flip_desc,
            icon = R.drawable.ic_rotate,
            isAvailable = false
        ),
        ImageFeature(
            id = "image_compression",
            title = R.string.image_compression,
            description = R.string.image_compression_desc,
            icon = R.drawable.ic_compress,
            isAvailable = false
        )
    )
}