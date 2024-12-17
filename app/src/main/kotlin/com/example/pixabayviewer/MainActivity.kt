package com.example.pixabayviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pixabayviewer.core.ui.theme.PixabayViewerTheme
import com.example.pixabayviewer.feature.imagedetail.ImageDetailScreen
import com.example.pixabayviewer.feature.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayViewerTheme {
                PixabayApp()
            }
        }
    }
}

@Composable
fun PixabayApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                onImageClick = { imageId ->
                    navController.navigate("detail/$imageId")
                }
            )
        }
        composable(
            "detail/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.LongType })
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getLong("imageId") ?: return@composable
            ImageDetailScreen(
                imageUrl = "", // Get URL from ViewModel
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}