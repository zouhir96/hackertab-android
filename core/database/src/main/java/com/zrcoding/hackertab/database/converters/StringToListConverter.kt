package com.zrcoding.hackertab.database.converters

import androidx.room.TypeConverter

class StringToListConverter {
    private val SEPARATOR = "||"

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        return value?.split(SEPARATOR)
    }

    @TypeConverter
    fun toMergedString(list: List<String>?): String? {
        return list?.joinToString(separator = SEPARATOR, transform = { it.trim() })
    }
}