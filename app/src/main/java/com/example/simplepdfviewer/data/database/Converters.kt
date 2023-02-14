package com.example.simplepdfviewer.data.database

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun uriToString(uri: Uri) =
        uri.toString()

    @TypeConverter
    fun stringToUri(uri: String) =
        uri.toUri()
}