package com.example.simplepdfviewer.data.database

import android.app.Application
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplepdfviewer.data.domain.Pdf

@Entity(tableName = "pdf_list")
data class DatabasePdf(
    @ColumnInfo(name = "pdf_uri") val pdfUri: Uri
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

fun List<DatabasePdf>.asDomainModel(application: Application): List<Pdf> {
    return map {
        try {
            val input =
                application.applicationContext.contentResolver.openFileDescriptor(it.pdfUri, "r")
            val renderer = PdfRenderer(input!!)
            val page = renderer.openPage(0)
            val bitmap =
                Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            renderer.close()

            Pdf(
                it,
                bitmap.asImageBitmap()
            )
        } catch (e: Exception) {
            Pdf(
                it,
                null
            )
        }
    }
}