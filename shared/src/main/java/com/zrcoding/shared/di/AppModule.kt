package com.zrcoding.shared.di

import android.content.Context
import androidx.room.Room
import com.zrcoding.shared.core.Constants.BASE_URL
import com.zrcoding.shared.core.Constants.DATABASE_NAME
import com.zrcoding.shared.data.local.HackertabDatabase
import com.zrcoding.shared.data.remote.HackertabApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger.DEFAULT
    ).apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun provideOkHTTP(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideHackertabService(retrofit: Retrofit): HackertabApi = retrofit.create(
        HackertabApi::class.java
    )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HackertabDatabase = Room
        .databaseBuilder(
            context,
            HackertabDatabase::class.java,
            DATABASE_NAME
        ).build()
}