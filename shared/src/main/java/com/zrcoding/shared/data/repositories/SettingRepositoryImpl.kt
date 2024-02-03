package com.zrcoding.shared.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.zrcoding.shared.data.datastore.UserSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingRepository{

    override fun getUserSetting(): Flow<UserSetting> = flowOf()

    override suspend fun toggleSubscribedLanguage(language: String) {

    }

    override fun updateUserSubscribedTopics() {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {

    }
}