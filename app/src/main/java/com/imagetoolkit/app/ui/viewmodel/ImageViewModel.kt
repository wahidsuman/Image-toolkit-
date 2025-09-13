package com.imagetoolkit.app.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imagetoolkit.app.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()
    
    fun setSelectedImage(uri: Uri) {
        _selectedImageUri.value = uri
    }
    
    fun clearSelectedImage() {
        _selectedImageUri.value = null
    }
    
    fun saveImage(bitmap: android.graphics.Bitmap, filename: String) {
        viewModelScope.launch {
            _isLoading.value = true
            imageRepository.saveImageToGallery(bitmap, filename)
                .onSuccess { uri ->
                    _message.value = "Image saved successfully"
                }
                .onFailure { exception ->
                    _message.value = "Error saving image: ${exception.message}"
                }
            _isLoading.value = false
        }
    }
    
    fun clearMessage() {
        _message.value = null
    }
}