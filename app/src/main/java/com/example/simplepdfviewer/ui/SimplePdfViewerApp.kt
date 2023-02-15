package com.example.simplepdfviewer.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplepdfviewer.ui.pdfviewer.PdfViewer

@Composable
fun SimplePdfViewerApp() {
    val navController = rememberNavController()
    SimplePdfViewerNavHost(navController)
}

@Composable
fun SimplePdfViewerNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPdfClick = { id: Int -> navController.navigate("pdf_viewer/${id}") },
                viewModel = hiltViewModel()
            )
        }
        composable("pdf_viewer/{id}") { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let {
                PdfViewer(
                    id = it.toInt(),
                    viewModel = hiltViewModel()
                )
            }
        }
    }
}