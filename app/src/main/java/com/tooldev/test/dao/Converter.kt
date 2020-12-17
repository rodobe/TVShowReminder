package com.tooldev.test.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tooldev.test.data.model.response.Hit

class Converter {

    @TypeConverter
    fun fromHits(hit: Hit?): String?{
        return if (hit == null) null else Gson().toJson(hit)
    }

    @TypeConverter
    fun toHits(value: String?): Hit?{
        return if(value == null) null else Gson().fromJson<Hit>(value, object: TypeToken<Hit>(){}.type)
    }

}