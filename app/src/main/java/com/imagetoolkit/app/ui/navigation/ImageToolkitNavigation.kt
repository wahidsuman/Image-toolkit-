package com.imagetoolkit.app.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imagetoolkit.app.ui.screens.crop.CropResizeScreen
import com.imagetoolkit.app.ui.screens.home.HomeScreen
import com.imagetoolkit.app.ui.screens.placeholder.PlaceholderScreen

@Composable
fun ImageToolkitNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("crop_resize") {
            CropResizeScreen(navController = navController)
        }
        composable("background_removal") {
            PlaceholderScreen(
                title = "Background Removal",
                description = "This feature will be available soon",
                navController = navController
            )
        }
        composable("filters_effects") {
            PlaceholderScreen(
                title = "Filters & Effects",
                description = "This feature will be available soon",
                navController = navController
            )
        }
        composable("format_conversion") {
            PlaceholderScreen(
                title = "Format Conversion",
                description = "This feature will be available soon",
                navController = navController
            )
        }
        composable("rotate_flip") {
            PlaceholderScreen(
                title = "Rotate & Flip",
                description = "This feature will be available soon",
                navController = navController
            )
        }
        composable("image_compression") {
            PlaceholderScreen(
                title = "Image Compression",
                description = "This feature will be available soon",
                navController = navController
            )
        }
    }
}