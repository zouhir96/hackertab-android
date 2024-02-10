package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.domain.models.Topic
import kotlinx.coroutines.flow.Flow


interface SettingRepository {
    suspend fun getTopics(): List<Topic>

    fun getSavedTopicsIds(): Flow<List<String>>

    suspend fun savedTopic(id: String)
    
    suspend fun unsavedTopic(id: String)
}