package com.example.simplepdfviewer.data.domain

import androidx.compose.ui.graphics.ImageBitmap
import com.example.simplepdfviewer.data.database.DatabasePdf

data class Pdf(
    val content: DatabasePdf,
    val thumbnail: ImageBitmap
)