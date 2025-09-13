package com.imagetoolkit.app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.ContextCompat
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
            // Use default colors instead of MaterialTheme
            setToolbarColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            setStatusBarColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
            setToolbarWidgetColor(ContextCompat.getColor(context, android.R.color.white))
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