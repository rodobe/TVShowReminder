package com.tooldev.tvShowReminder.di

import android.content.Context
import com.tooldev.test.dao.AppDataBase
import com.tooldev.tvShowReminder.dao.AppDao
import com.tooldev.tvShowReminder.data.local.ContentLocalData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDataBaseModule {

    @Provides
    @Singleton
    fun getDao(@ApplicationContext context: Context): AppDao {
        val database by lazy { AppDataBase.getDataBase(context) }
        return database.appDao()
    }

    @Provides
    @Singleton
    fun getContentLocalData(@ApplicationContext context: Context): ContentLocalData {
        return ContentLocalData(getDao(context))
    }

}