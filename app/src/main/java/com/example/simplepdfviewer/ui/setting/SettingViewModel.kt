package com.example.simplepdfviewer.ui.setting

/*
data class SettingUiState(
    var name: String = "",
    var cover: Uri? = null,
    var pdfUri: Uri? = null
)

class SettingViewModel @Inject constructor(
    private val pdfRepository: PdfRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    fun addName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun addCover(cover: Uri) {
        _uiState.update { it.copy(cover = cover) }
    }

    fun addPdf(pdfUri: Uri) {
        _uiState.update { it.copy(pdfUri = pdfUri) }
    }

    fun savePdf(name: String, cover: Uri, pdfUri: Uri) {
        viewModelScope.launch {
            val input = ParcelFileDescriptor.open(pdfUri.toFile(), ParcelFileDescriptor.MODE_READ_ONLY)
            val renderer = PdfRenderer(input)
            val page = renderer.openPage(0)
            //val bitmap = Bitmap.createBitmap(page.width, page.height)
            //pdfRepository.insert(Pdf(name, cover, pdfUri))
        }
    }
}*/
