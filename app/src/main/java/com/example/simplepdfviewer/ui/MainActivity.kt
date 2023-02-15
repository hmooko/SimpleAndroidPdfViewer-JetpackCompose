package com.example.simplepdfviewer.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.simplepdfviewer.ui.theme.SimplePDFViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {}
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                Log.d("MainActivity", "has READ_MEDIA_AUDIO permission")
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        setContent {
            SimplePDFViewerTheme {
                SimplePdfViewerApp()
            }
        }
    }
}
