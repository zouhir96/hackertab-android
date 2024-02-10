package com.zrcoding.shared.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zrcoding.shared.R
import com.zrcoding.shared.core.toJson
import com.zrcoding.shared.domain.models.Topic
import com.zrcoding.shared.domain.repositories.SettingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val KEY_SAVED_TOPICS = stringPreferencesKey("saved_topics")

class SettingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : SettingRepository {

    companion object {
        private val topicsMemoryCache = emptyList<Topic>()
    }

    override suspend fun getTopics(): List<Topic> {
        if (topicsMemoryCache.isNotEmpty()) return topicsMemoryCache

        val topicsInputStream = context.resources.openRawResource(R.raw.topics)
        val topicsJson = topicsInputStream.toJson()
        return gson.fromJson(topicsJson, object : TypeToken<List<Topic>>() {}.type)
    }

    override fun getSavedTopicsIds(): Flow<List<String>> {
        return dataStore.data.map { it.getSavedTopics() }
    }

    override suspend fun savedTopic(id: String) {
        dataStore.edit {
            it[KEY_SAVED_TOPICS] = gson.toJson(it.getSavedTopics() + id)
        }
    }

    override suspend fun unsavedTopic(id: String) {
        dataStore.edit {
            it[KEY_SAVED_TOPICS] = gson.toJson(it.getSavedTopics() - id)
        }
    }

    private fun Preferences.getSavedTopics(): List<String> {
        val topicsIdsPref = get(KEY_SAVED_TOPICS) ?: gson.toJson(emptyList<String>())
        return gson.fromJson(topicsIdsPref, object : TypeToken<List<String>>() {}.type)
    }
}