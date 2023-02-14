package com.example.simplepdfviewer.di

import android.content.Context
import androidx.room.Room
import com.example.simplepdfviewer.data.database.AppDatabase
import com.example.simplepdfviewer.data.database.PdfDao
import com.example.simplepdfviewer.data.datasource.PdfDatasource
import com.example.simplepdfviewer.data.repository.PdfRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pdf_list"
        ).build()

    @Provides
    fun providePdfDao(database: AppDatabase) =
        database.pdfDao()

    @Singleton
    @Provides
    fun providePdfDatasource(pdfDao: PdfDao) =
        PdfDatasource(pdfDao)

    @Singleton
    @Provides
    fun providePdfRepository(pdfDatasource: PdfDatasource) =
        PdfRepository(pdfDatasource)
}