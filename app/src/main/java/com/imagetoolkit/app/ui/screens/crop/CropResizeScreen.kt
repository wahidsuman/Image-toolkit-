package com.imagetoolkit.app.ui.screens.crop

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imagetoolkit.app.R
import com.imagetoolkit.app.ui.viewmodel.ImageViewModel
import com.imagetoolkit.app.utils.CropUtils
import com.yalantis.ucrop.UCrop
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropResizeScreen(
    navController: NavController,
    viewModel: ImageViewModel? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    val actualViewModel = viewModel ?: remember { ImageViewModel(context) }
    
    val selectedImageUri by actualViewModel.selectedImageUri.collectAsState()
    val isLoading by actualViewModel.isLoading.collectAsState()
    val message by actualViewModel.message.collectAsState()
    
    var showAspectRatioDialog by remember { mutableStateOf(false) }
    var selectedAspectRatio by remember { mutableStateOf("Free") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    
    val cropLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            android.app.Activity.RESULT_OK -> {
                val resultUri = result.data?.getParcelableExtra<Uri>(UCrop.EXTRA_OUTPUT_URI)
                resultUri?.let { uri ->
                    scope.launch {
                        try {
                            // Process the cropped image
                            val bitmap = CropUtils.getBitmapFromUri(context, uri)
                            bitmap?.let {
                                val filename = "cropped_${System.currentTimeMillis()}"
                                actualViewModel.saveImage(it, filename)
                            } ?: run {
                                actualViewModel.setMessage("Failed to load cropped image")
                            }
                        } catch (e: Exception) {
                            actualViewModel.setMessage("Error processing cropped image: ${e.message}")
                        }
                    }
                }
            }
            UCrop.RESULT_ERROR -> {
                val error = result.data?.getStringExtra(UCrop.EXTRA_ERROR)
                scope.launch {
                    actualViewModel.setMessage("Crop error: ${error ?: "Unknown error"}")
                }
            }
            else -> {
                scope.launch {
                    actualViewModel.setMessage("Crop cancelled")
                }
            }
        }
    }
    
    LaunchedEffect(selectedImageUri) {
        selectedImageUri?.let { uri ->
            try {
                val cropIntent = CropUtils.getCropIntent(
                    context = context,
                    sourceUri = uri,
                    aspectRatio = selectedAspectRatio,
                    width = width.takeIf { it.isNotEmpty() }?.toIntOrNull(),
                    height = height.takeIf { it.isNotEmpty() }?.toIntOrNull()
                )
                cropLauncher.launch(cropIntent)
            } catch (e: Exception) {
                actualViewModel.setMessage("Error creating crop intent: ${e.message}")
            }
        }
    }
    
    // Show message
    LaunchedEffect(message) {
        message?.let {
            // Message will be shown via snackbar
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.crop_resize)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (selectedImageUri == null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = stringResource(R.string.no_image_selected),
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
                return@Column
            }
            
            // Aspect Ratio Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.aspect_ratio),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("1:1", "4:3", "16:9", "Free").forEach { ratio ->
                            FilterChip(
                                onClick = { selectedAspectRatio = ratio },
                                label = { Text(ratio) },
                                selected = selectedAspectRatio == ratio,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Resize Options
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.resize),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = width,
                            onValueChange = { width = it },
                            label = { Text(stringResource(R.string.width)) },
                            modifier = Modifier.weight(1f),
                            suffix = { Text("px") }
                        )
                        
                        OutlinedTextField(
                            value = height,
                            onValueChange = { height = it },
                            label = { Text(stringResource(R.string.height)) },
                            modifier = Modifier.weight(1f),
                            suffix = { Text("px") }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        selectedImageUri?.let { uri ->
                            val cropIntent = CropUtils.getCropIntent(
                                context = context,
                                sourceUri = uri,
                                aspectRatio = selectedAspectRatio,
                                width = width.takeIf { it.isNotEmpty() }?.toIntOrNull(),
                                height = height.takeIf { it.isNotEmpty() }?.toIntOrNull()
                            )
                            cropLauncher.launch(cropIntent)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(stringResource(R.string.crop_resize))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Instructions
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    text = "Instructions:\n" +
                            "1. Select an aspect ratio or choose 'Free' for custom\n" +
                            "2. Optionally set width and height for resizing\n" +
                            "3. Tap 'Crop & Resize' to open the cropping tool\n" +
                            "4. Adjust the crop area and tap the checkmark to save",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
    
    // Show message as snackbar
    message?.let { msg ->
        LaunchedEffect(msg) {
            // In a real app, you'd show this as a SnackBar
            // For now, we'll just clear it after a delay
            kotlinx.coroutines.delay(3000)
            viewModel.clearMessage()
        }
    }
}