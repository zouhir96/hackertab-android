package com.zrcoding.hackertab.network.di

import com.zrcoding.hackertab.network.api.HackertabApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.hackertab.dev/data/v2/"
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger.DEFAULT
    ).apply {
        level = HttpLoggingInterceptor.Level.BODY
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
}