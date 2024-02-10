package com.zrcoding.shared.di

import com.zrcoding.shared.data.repositories.PostRepositoryImpl
import com.zrcoding.shared.data.repositories.SettingRepositoryImpl
import com.zrcoding.shared.domain.repositories.PostRepository
import com.zrcoding.shared.domain.repositories.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providePostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    abstract fun provideSettingRepository(
        settingRepository: SettingRepositoryImpl
    ): SettingRepository
}