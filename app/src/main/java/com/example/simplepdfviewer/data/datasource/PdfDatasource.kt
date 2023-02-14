package com.example.simplepdfviewer.data.datasource

import com.example.simplepdfviewer.data.database.DatabasePdf
import com.example.simplepdfviewer.data.database.PdfDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PdfDatasource @Inject constructor(
    private val pdfDao: PdfDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAll() = pdfDao.getAll()

    fun findById(id: Int) = pdfDao.findById(id)

    suspend fun deleteAll() =
        withContext(ioDispatcher) {
            pdfDao.deleteAll()
        }

    suspend fun insert(databasePdf: DatabasePdf) =
        withContext(ioDispatcher) {
            pdfDao.insert(databasePdf)
        }

    suspend fun delete(databasePdf: DatabasePdf) =
        withContext(ioDispatcher) {
            pdfDao.delete(databasePdf)
        }
}