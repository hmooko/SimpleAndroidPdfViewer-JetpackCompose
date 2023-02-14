package com.example.simplepdfviewer.ui.setting

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
/*

@Composable
fun SettingScreen(viewModel: SettingViewModel = viewModel(), modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { */
/*TODO*//*
 }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (uiState.cover != null && uiState.pdfUri != null && uiState.name != "") {
                            viewModel.savePdf(
                                uiState.name,
                                uiState.cover!!,
                                uiState.pdfUri!!
                            )
                        }
                        else {
                            Toast.makeText(context, "pdf, 이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                            Log.d("MusicAddScreen", "not add")
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        Column(screenModifier) {
            Spacer(modifier = modifier.weight(1f))
            SettingPdf(viewModel = viewModel, uiState = uiState)
            Spacer(modifier = modifier.height(20.dp))
            SettingName(viewModel = viewModel, uiState = uiState)
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun SettingPdf(viewModel: SettingViewModel, uiState: SettingUiState, modifier: Modifier = Modifier) {

    val pickPdf = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            viewModel.addPdf(it)
        }
    }

    Box(
        modifier = modifier
            .size(250.dp)
            .drawBehind {
                if (uiState.cover == null)
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
                pickPdf.launch("application/pdf")
            },
        contentAlignment = Alignment.Center
    ) {
        if (uiState.cover == null) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Yellow)
        }
        else {
            Card(shape = RoundedCornerShape(16.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.cover)
                        .build(),
                    contentDescription = null,
                    modifier = modifier.size(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun SettingName(viewModel: SettingViewModel, uiState: SettingUiState, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = uiState.name,
        onValueChange = viewModel::addName,
        textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            unfocusedBorderColor = Yellow,
            focusedBorderColor = Yellow
        )
    )
}*/
