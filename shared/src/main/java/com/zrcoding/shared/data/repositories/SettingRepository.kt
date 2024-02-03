package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.data.datastore.UserSetting
import kotlinx.coroutines.flow.Flow


interface SettingRepository {
    fun getUserSetting(): Flow<UserSetting>

    suspend fun toggleSubscribedLanguage(userLanguages: String)

    fun updateUserSubscribedTopics()

    suspend fun clear()
}