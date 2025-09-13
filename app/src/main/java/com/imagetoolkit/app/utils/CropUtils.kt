package com.imagetoolkit.app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
        val destinationFile = File(context.cacheDir, "cropped_${System.currentTimeMillis()}.jpg")
        val destinationUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            destinationFile
        )
        
        val options = UCrop.Options().apply {
            setCompressionFormat(android.graphics.Bitmap.CompressFormat.JPEG)
            setCompressionQuality(90)
            setFreeStyleCropEnabled(true)
            // Use default colors instead of MaterialTheme
            setToolbarColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            setStatusBarColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            setToolbarWidgetColor(ContextCompat.getColor(context, android.R.color.white))
        }
        
        val uCrop = UCrop.of(sourceUri, destinationUri)
            .withOptions(options)
        
        // Set aspect ratio using UCrop builder
        when (aspectRatio) {
            "1:1" -> uCrop.withAspectRatio(1f, 1f)
            "4:3" -> uCrop.withAspectRatio(4f, 3f)
            "16:9" -> uCrop.withAspectRatio(16f, 9f)
            "Free" -> {
                // Free style is already enabled in options
            }
        }
        
        return uCrop.getIntent(context)
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