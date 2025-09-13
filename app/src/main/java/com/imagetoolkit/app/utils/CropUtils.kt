package com.imagetoolkit.app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.yalantis.ucrop.UCrop
import java.io.File
import java.io.InputStream

object CropUtils {
    fun getCropIntent(
        context: Context,
        sourceUri: Uri,
        aspectRatio: String = "Free",
        width: Int? = null,
        height: Int? = null
    ): Intent {
        val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped_${System.currentTimeMillis()}.jpg"))
        
        val options = UCrop.Options().apply {
            setCompressionFormat(android.graphics.Bitmap.CompressFormat.JPEG)
            setCompressionQuality(90)
            setFreeStyleCropEnabled(true)
            setToolbarColor(androidx.compose.material3.MaterialTheme.colorScheme.primary.toArgb())
            setStatusBarColor(androidx.compose.material3.MaterialTheme.colorScheme.primary.toArgb())
            setToolbarWidgetColor(androidx.compose.material3.MaterialTheme.colorScheme.onPrimary.toArgb())
        }
        
        // Set aspect ratio
        when (aspectRatio) {
            "1:1" -> options.setAspectRatioOptions(1, 1)
            "4:3" -> options.setAspectRatioOptions(4, 3)
            "16:9" -> options.setAspectRatioOptions(16, 9)
            "Free" -> options.setFreeStyleCropEnabled(true)
        }
        
        return UCrop.of(sourceUri, destinationUri)
            .withOptions(options)
            .getIntent(context)
    }
    
    suspend fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            bitmap
        } catch (e: Exception) {
            null
        }
    }
}