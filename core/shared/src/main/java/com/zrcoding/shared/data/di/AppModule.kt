package com.zrcoding.shared.data.di

import android.content.Context
import androidx.room.Room
import com.zrcoding.shared.core.Constants.DATABASE_NAME
import com.zrcoding.shared.data.local.HackertabDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HackertabDatabase = Room
        .databaseBuilder(
            context,
            HackertabDatabase::class.java,
            DATABASE_NAME
        ).build()
}