package com.zrcoding.shared.data.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<UserSettingsPreferences>
) {
    fun getUserLanguages(): Flow<UserSetting> {
        return dataStore.data
            .map {
                UserSetting(
                    it.subscribedLanguagesList,
                    it.subscribedTopicsList
                )
            }
    }

    suspend fun clearDataStore() {
        dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }

    suspend fun toggleLanguage(
        language: String
    ) {
        dataStore.updateData { preferences ->
            val oldLanguagesList = preferences.subscribedLanguagesList

            if (!oldLanguagesList.contains(language)) {
                return@updateData preferences
                    .toBuilder()
                    .addSubscribedLanguages(language)
                    .build()
            }

            preferences
        }
    }

}