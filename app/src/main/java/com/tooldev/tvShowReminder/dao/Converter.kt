package com.tooldev.tvShowReminder.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tooldev.tvShowReminder.data.model.response.home.Genre
import com.tooldev.tvShowReminder.data.model.response.home.TvShow

class Converter {

    @TypeConverter
    fun fromTvShow(tvShow: TvShow?): String?{
        return if (tvShow == null) null else Gson().toJson(tvShow)
    }

    @TypeConverter
    fun toTvShow(value: String?): TvShow?{
        return if(value == null) null else Gson().fromJson<TvShow>(value, object: TypeToken<TvShow>(){}.type)
    }

    @TypeConverter
    fun fromListInt(value: List<Int>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun toListInt(value: String?): List<Int>?{
        return if(value == null) null else Gson().fromJson<List<Int>>(value, object: TypeToken<List<Int>>(){}.type)
    }

    @TypeConverter
    fun fromListString(value: List<String>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun toListString(value: String?): List<String>?{
        return if(value == null) null else Gson().fromJson<List<String>>(value, object: TypeToken<List<String>>(){}.type)
    }

    @TypeConverter
    fun fromListGenres(value: List<Genre>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun toListGenres(value: String?): List<Genre>?{
        return if(value == null) null else Gson().fromJson<List<Genre>>(value, object: TypeToken<List<Genre>>(){}.type)
    }
}