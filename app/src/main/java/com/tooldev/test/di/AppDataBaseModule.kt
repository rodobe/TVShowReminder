package com.tooldev.test.di

import android.content.Context
import com.tooldev.test.dao.AppDao
import com.tooldev.test.dao.AppDataBase
import com.tooldev.test.data.local.ArticlesLocalData
import com.tooldev.test.data.remote.ArticlesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppDataBaseModule {

    @Provides
    @Singleton
    fun getDao(@ApplicationContext context: Context): AppDao {
        val database by lazy { AppDataBase.getDataBase(context) }
        return database.appDao()
    }

    @Provides
    @Singleton
    fun getArticlesLocalData(@ApplicationContext context: Context): ArticlesLocalData {
        return ArticlesLocalData(getDao(context))
    }

}