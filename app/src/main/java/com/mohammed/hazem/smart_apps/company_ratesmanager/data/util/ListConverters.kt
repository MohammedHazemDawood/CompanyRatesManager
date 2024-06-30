package com.mohammed.hazem.smart_apps.company_ratesmanager.data.util

import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListConverters {
    @TypeConverter
    fun encodeStringList(list: List<String>) = Json.encodeToString(list)
    @TypeConverter
    fun decodeStringList(string: String) = Json.decodeFromString<List<String>>(string)

    @TypeConverter
    fun encodeIntList(list: List<Int>) = Json.encodeToString(list)
    @TypeConverter
    fun decodeIntList(string: String) = Json.decodeFromString<List<Int>>(string)

}