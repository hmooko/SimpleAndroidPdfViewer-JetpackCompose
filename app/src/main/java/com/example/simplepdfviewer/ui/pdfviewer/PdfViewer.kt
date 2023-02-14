package com.example.simplepdfviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
fun PdfViewer(id: Int, viewModel: PdfViewerViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadPdf(id)

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(uiState.pdfImages) { image ->
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}