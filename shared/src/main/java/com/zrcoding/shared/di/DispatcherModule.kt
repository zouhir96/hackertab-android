package com.zrcoding.shared.di

import com.zrcoding.shared.utils.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    @Dispatcher(com.zrcoding.shared.utils.Dispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}