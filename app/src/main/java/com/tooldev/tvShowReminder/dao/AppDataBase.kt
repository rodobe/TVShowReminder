package com.tooldev.test.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tooldev.tvShowReminder.dao.AppDao
import com.tooldev.tvShowReminder.dao.Converter
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails

@Database(entities = [TvShow::class, GenresResult::class, TvShowDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{

        private const val db_name = "app_tv_shows_db"

        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase = instance?: synchronized(Any()){ instance ?: buildDataBase(context).also { instance = it }}

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, db_name)
            .fallbackToDestructiveMigration()
            .build()

    }

}