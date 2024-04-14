package com.zrcoding.shared.di

import com.zrcoding.shared.data.repositories.ArticleRepositoryImpl
import com.zrcoding.shared.domain.repositories.ArticleRepository
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
        postRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository
}