package com.zrcoding.hackertab.database.di

import android.content.Context
import androidx.room.Room
import com.zrcoding.hackertab.database.HackertabDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "hackertab-db"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HackertabDatabase = Room
        .databaseBuilder(
            context,
            HackertabDatabase::class.java,
            DATABASE_NAME
        ).build()
}