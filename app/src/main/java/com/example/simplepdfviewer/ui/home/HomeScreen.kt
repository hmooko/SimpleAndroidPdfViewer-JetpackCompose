package com.example.simplepdfviewer.ui.home

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onPdfClick: (Int) -> Unit, viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        LazyVerticalGrid(
            modifier = modifier
                .padding(innerPadding)
                .padding(10.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(15.dp)
        ) {
            items(items = uiState.pdfList) { pdf ->
                Card(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(12.dp),
                    modifier = modifier
                        .clickable {
                            onPdfClick(pdf.content.id)
                        }
                ) {
                    Image(
                        bitmap = pdf.thumbnail!!,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(150.dp)
                    )
                }
            }
            item {
                AddButton(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun AddButton(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "application/pdf"
    }

    val requestLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data?.data?.let {
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            viewModel.addPdf(it)
        }
    }

    Box(
        modifier = modifier
            .height(150.dp)
            .drawBehind {
                drawRoundRect(
                    color = Yellow,
                    style = Stroke(
                        width = 7f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
                    ),
                    cornerRadius = CornerRadius(50f, 50f)
                )
            }
            .clickable {
                requestLauncher.launch(intent)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Yellow
        )
    }
}