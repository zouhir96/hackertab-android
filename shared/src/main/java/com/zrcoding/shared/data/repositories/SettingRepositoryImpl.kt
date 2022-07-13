package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.data.datastore.SettingPreferencesDataSource
import com.zrcoding.shared.data.datastore.UserSetting
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val dataStorePref: SettingPreferencesDataSource
) : SettingRepository{

    override fun getUserSetting(): Flow<UserSetting> = dataStorePref.getUserLanguages()

    override suspend fun toggleSubscribedLanguage(language: String) {
        dataStorePref.toggleLanguage(language)
    }

    override fun updateUserSubscribedTopics() {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        dataStorePref.clearDataStore()
    }
}