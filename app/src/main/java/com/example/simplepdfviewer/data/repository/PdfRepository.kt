package com.example.simplepdfviewer.data.repository

import com.example.simplepdfviewer.data.database.DatabasePdf
import com.example.simplepdfviewer.data.datasource.PdfDatasource
import javax.inject.Inject

class PdfRepository @Inject constructor(
    private val pdfDatasource: PdfDatasource
) {
    fun getAll() = pdfDatasource.getAll()

    fun findById(id: Int) = pdfDatasource.findById(id)

    suspend fun deleteAll() = pdfDatasource.deleteAll()

    suspend fun insert(databasePdf: DatabasePdf) = pdfDatasource.insert(databasePdf)

    suspend fun delete(databasePdf: DatabasePdf) = pdfDatasource.delete(databasePdf)
}