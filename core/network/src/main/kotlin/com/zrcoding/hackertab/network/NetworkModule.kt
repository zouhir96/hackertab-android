package com.zrcoding.hackertab.network

import com.zrcoding.hackertab.network.api.HackertabApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.hackertab.dev/data/v2/"

val networkModule = module {
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger.DEFAULT
        ).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single<OkHttpClient> {
        val logger: HttpLoggingInterceptor = get()
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
    single<Retrofit> {
        val okHttpClient: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<HackertabApi> {
        val retrofit: Retrofit = get()
        retrofit.create(HackertabApi::class.java)
    }
}