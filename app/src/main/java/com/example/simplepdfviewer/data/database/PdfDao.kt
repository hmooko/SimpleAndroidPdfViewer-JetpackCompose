package com.example.simplepdfviewer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PdfDao {
    @Query("SELECT * FROM pdf_list")
    fun getAll(): Flow<List<DatabasePdf>>

    @Query("DELETE FROM pdf_list")
    fun deleteAll()

    @Insert
    fun insert(databasePdf: DatabasePdf)

    @Delete
    fun delete(databasePdf: DatabasePdf)

    @Query("SELECT * FROM pdf_list WHERE id = :id")
    fun findById(id: Int): Flow<DatabasePdf>
}