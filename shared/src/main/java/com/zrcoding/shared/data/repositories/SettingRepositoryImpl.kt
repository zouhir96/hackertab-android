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
import com.zrcoding.shared.domain.models.Source
import com.zrcoding.shared.domain.models.Topic
import com.zrcoding.shared.domain.repositories.SettingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val KEY_SAVED_TOPICS = stringPreferencesKey("saved_topics")
private val KEY_SAVED_SOURCES = stringPreferencesKey("saved_sources")

class SettingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : SettingRepository {

    companion object {
        private val topicsMemoryCache = emptyList<Topic>()
        private val sourcesMemoryCash = listOf(
            Source(
                value = "github",
                label = "Github repositories",
                icon = R.drawable.ic_github,
                type = "supported",
                link = "https://github.com/",
                analyticsTag = "github",
            ),
            Source(
                value = "hackernews",
                label = "Hackernews",
                icon = R.drawable.ic_hackernews,
                type = "supported",
                link = "https://news.ycombinator.com/",
                analyticsTag = "hackernews",
            ),
            Source(
                value = "conferences",
                label = "Upcoming events",
                icon = R.drawable.ic_conferences,
                type = "supported",
                link = "https://confs.tech/",
                analyticsTag = "events",
            ),
            Source(
                value = "devto",
                label = "DevTo",
                icon = R.drawable.ic_devto,
                type = "supported",
                link = "https://dev.to/",
                analyticsTag = "devto",
            ),
            Source(
                value = "producthunt",
                label = "Product Hunt",
                icon = R.drawable.ic_product_hunt,
                type = "supported",
                link = "https://producthunt.com/",
                analyticsTag = "producthunt",
            ),
            Source(
                value = "reddit",
                label = "Reddit",
                icon = R.drawable.ic_reddit,
                type = "supported",
                link = "https://reddit.com/",
                analyticsTag = "reddit",
            ),
            Source(
                value = "lobsters",
                label = "Lobsters",
                icon = R.drawable.ic_lobsters,
                type = "supported",
                link = "https://lobste.rs/",
                analyticsTag = "lobsters",
            ),
            Source(
                value = "hashnode",
                label = "Hashnode",
                icon = R.drawable.ic_hashnode,
                type = "supported",
                link = "https://hashnode.com/",
                analyticsTag = "hashnode",
            ),
            Source(
                value = "freecodecamp",
                label = "FreeCodeCamp",
                icon = R.drawable.ic_freecodecamp,
                type = "supported",
                link = "https://freecodecamp.com/news",
                analyticsTag = "freecodecamp",
            ),
            Source(
                value = "indiehackers",
                label = "IndieHackers",
                icon = R.drawable.ic_indie_hackers,
                type = "supported",
                link = "https://indiehackers.com/",
                analyticsTag = "indiehackers",
            ),
            Source(
                value = "medium",
                label = "Medium",
                icon = R.drawable.ic_medium,
                type = "supported",
                link = "https://medium.com/",
                analyticsTag = "medium",
            ),
            Source(
                value = "ai",
                label = "Powered by AI",
                icon = R.drawable.ic_ai,
                type = "supported",
                analyticsTag = "ai",
                badge = "ALPHA"
            )
        )
    }

    override suspend fun getTopics(): List<Topic> {
        if (topicsMemoryCache.isNotEmpty()) return topicsMemoryCache

        val topicsInputStream = context.resources.openRawResource(R.raw.topics)
        val topicsJson = topicsInputStream.toJson()
        return gson.fromJson(topicsJson, object : TypeToken<List<Topic>>() {}.type)
    }

    override fun getSavedTopicsIds(): Flow<List<String>> {
        return getSavedIds(KEY_SAVED_TOPICS)
    }

    override suspend fun saveTopic(id: String) {
        saveId(id, KEY_SAVED_TOPICS)
    }

    override suspend fun removeTopic(id: String) {
        removeId(id, KEY_SAVED_TOPICS)
    }

    override fun getSources(): List<Source> {
        return sourcesMemoryCash
    }

    override fun getSavedSourcesIds(): Flow<List<String>> {
        return getSavedIds(KEY_SAVED_SOURCES)
    }

    override suspend fun saveSource(id: String) {
        saveId(id, KEY_SAVED_SOURCES)
    }

    override suspend fun removeSource(id: String) {
        removeId(id, KEY_SAVED_SOURCES)
    }

    private fun getSavedIds(key: Preferences.Key<String>): Flow<List<String>> {
        return dataStore.data.map { it.fromSavedJsonToList(key) }
    }

    private suspend fun saveId(id: String, key: Preferences.Key<String>) {
        dataStore.edit {
            it[key] = gson.toJson(it.fromSavedJsonToList(key) + id)
        }
    }

    private suspend fun removeId(id: String, key: Preferences.Key<String>) {
        dataStore.edit {
            it[key] = gson.toJson(it.fromSavedJsonToList(key) - id)
        }
    }

    private fun Preferences.fromSavedJsonToList(key: Preferences.Key<String>): List<String> {
        val topicsIdsPref = get(key) ?: gson.toJson(emptyList<String>())
        return gson.fromJson(topicsIdsPref, object : TypeToken<List<String>>() {}.type)
    }
}