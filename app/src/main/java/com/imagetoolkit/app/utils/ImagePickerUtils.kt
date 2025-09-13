package com.imagetoolkit.app.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object ImagePickerUtils {
    fun createImageUri(context: Context): Uri? {
        return try {
            val imageFile = File.createTempFile(
                "temp_image_",
                ".jpg",
                context.cacheDir
            )
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                imageFile
            )
        } catch (e: Exception) {
            null
        }
    }
}