package com.tooldev.test.di

import android.content.Context
import com.tooldev.test.R
import com.tooldev.test.data.api.ArticlesApi
import com.tooldev.test.data.remote.ArticlesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(context.getString(R.string.test_base_url))
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    @Singleton
    fun getArticlesApi(@ApplicationContext context: Context): ArticlesApi{
        return getRetrofit(context).create(ArticlesApi::class.java)
    }


    @Provides
    @Singleton
    fun getArticlesDataSource(@ApplicationContext context: Context): ArticlesDataSource{
        return ArticlesDataSource(getArticlesApi(context))
    }

}