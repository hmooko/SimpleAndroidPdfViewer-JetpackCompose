package com.example.simplepdfviewer.ui.home

import android.app.Application
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplepdfviewer.data.database.DatabasePdf
import com.example.simplepdfviewer.data.database.asDomainModel
import com.example.simplepdfviewer.data.domain.Pdf
import com.example.simplepdfviewer.data.repository.PdfRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    var pdfList: List<Pdf> = listOf()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pdfRepository: PdfRepository,
    private val application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            pdfRepository.getAll().collect { pdfList ->
                _uiState.update {
                    it.copy(pdfList = pdfList.asDomainModel(application))
                }
            }
        }
    }

    fun popPdf(pdf: DatabasePdf) {
        viewModelScope.launch {
            pdfRepository.delete(pdf)
        }
    }

    fun addPdf(pdfUri: Uri) {
        viewModelScope.launch {
            pdfRepository.insert(DatabasePdf(pdfUri))
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            pdfRepository.deleteAll()
        }
    }
}