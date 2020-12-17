package com.tooldev.test.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tooldev.test.data.model.response.Hit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Hit::class, DeletedHit::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{

        private const val db_name = "app_test_db"

        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase = instance?: synchronized(Any()){ instance ?: buildDataBase(context).also { instance = it }}

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, db_name)
            .fallbackToDestructiveMigration()
            .build()

        private class appDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.appDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(appDao: AppDao) {
            appDao.deleteAllHits()
        }

    }

}