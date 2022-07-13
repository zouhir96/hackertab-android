package com.zrcoding.shared.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.zrcoding.shared.utils.Dispatcher
import com.zrcoding.shared.utils.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceDataStoreModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(Dispatchers.IO) ioDispatcher: CoroutineDispatcher,
        userSettingSerializer: UserSettingSerializer
    ): DataStore<UserSettingsPreferences> =

        DataStoreFactory.create(
            serializer = userSettingSerializer,
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
        ) {
            context.dataStoreFile("user_preferences.pb")
        }
}
