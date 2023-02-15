package com.example.simplepdfviewer.ui.pdfviewer

import android.app.Application
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplepdfviewer.data.database.DatabasePdf
import com.example.simplepdfviewer.data.repository.PdfRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

data class PdfViewerUiState(
    var pdfImages: List<ImageBitmap> = listOf()
)

@HiltViewModel
class PdfViewerViewModel @Inject constructor(
    private val pdfRepository: PdfRepository,
    private val application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(PdfViewerUiState())
    val uiState: StateFlow<PdfViewerUiState> = _uiState.asStateFlow()

    fun loadPdf(id: Int) {
        viewModelScope.launch {
            pdfRepository.findById(id).collect { pdf: DatabasePdf ->
                var pdfImages: MutableList<ImageBitmap> = mutableListOf()

                val input = application.applicationContext.contentResolver.openFileDescriptor(
                    pdf.pdfUri,
                    "r"
                )
                val renderer = PdfRenderer(input!!)

                for (i in 0 until renderer.pageCount) {
                    val page = renderer.openPage(i)
                    val bitmap =
                        Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    pdfImages.add(bitmap.asImageBitmap())
                    page.close()
                }
                renderer.close()

                _uiState.update { it.copy(pdfImages = pdfImages) }
            }
        }
    }
}
