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
import com.zrcoding.shared.domain.models.SourceName
import com.zrcoding.shared.domain.models.Topic
import com.zrcoding.shared.domain.repositories.SettingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
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
                name = SourceName.GITHUB,
                label = "Github repositories",
                icon = R.drawable.ic_github,
                type = "supported",
                link = "https://github.com/",
                analyticsTag = "github",
            ),
            Source(
                name = SourceName.HACKER_NEWS,
                label = "Hackernews",
                icon = R.drawable.ic_hackernews,
                type = "supported",
                link = "https://news.ycombinator.com/",
                analyticsTag = "hackernews",
            ),
            Source(
                name = SourceName.CONFERENCES,
                label = "Upcoming events",
                icon = R.drawable.ic_conferences,
                type = "supported",
                link = "https://confs.tech/",
                analyticsTag = "events",
            ),
            Source(
                name = SourceName.DEVTO,
                label = "DevTo",
                icon = R.drawable.ic_devto,
                type = "supported",
                link = "https://dev.to/",
                analyticsTag = "devto",
            ),
            Source(
                name = SourceName.PRODUCTHUNT,
                label = "Product Hunt",
                icon = R.drawable.ic_product_hunt,
                type = "supported",
                link = "https://producthunt.com/",
                analyticsTag = "producthunt",
            ),
            Source(
                name = SourceName.REDDIT,
                label = "Reddit",
                icon = R.drawable.ic_reddit,
                type = "supported",
                link = "https://reddit.com/",
                analyticsTag = "reddit",
            ),
            Source(
                name = SourceName.LOBSTERS,
                label = "Lobsters",
                icon = R.drawable.ic_lobsters,
                type = "supported",
                link = "https://lobste.rs/",
                analyticsTag = "lobsters",
            ),
            Source(
                name = SourceName.HASH_NODE,
                label = "Hashnode",
                icon = R.drawable.ic_hashnode,
                type = "supported",
                link = "https://hashnode.com/",
                analyticsTag = "hashnode",
            ),
            Source(
                name = SourceName.FREE_CODE_CAMP,
                label = "FreeCodeCamp",
                icon = R.drawable.ic_freecodecamp,
                type = "supported",
                link = "https://freecodecamp.com/news",
                analyticsTag = "freecodecamp",
            ),
            Source(
                name = SourceName.INDIE_HACKERS,
                label = "IndieHackers",
                icon = R.drawable.ic_indie_hackers,
                type = "supported",
                link = "https://indiehackers.com/",
                analyticsTag = "indiehackers",
            ),
            Source(
                name = SourceName.MEDIUM,
                label = "Medium",
                icon = R.drawable.ic_medium,
                type = "supported",
                link = "https://medium.com/",
                analyticsTag = "medium",
            ),
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeSelectedTopics(): Flow<List<Topic>> {
        return getSavedTopicsIds().mapLatest { savedTopicsIds ->
            getTopics().filter { it.id in savedTopicsIds }
        }
    }

    override fun getSources(): List<Source> {
        return sourcesMemoryCash
    }

    override fun getSavedSourcesNames(): Flow<List<SourceName>> {
        return getSavedIds(KEY_SAVED_SOURCES).map { names ->
            names.mapNotNull { name ->
                SourceName.entries.firstOrNull {
                    it.value == name
                }
            }
        }
    }

    override suspend fun saveSource(id: String) {
        saveId(id, KEY_SAVED_SOURCES)
    }

    override suspend fun removeSource(id: String) {
        removeId(id, KEY_SAVED_SOURCES)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeSelectedSources(): Flow<List<Source>> {
        return getSavedSourcesNames().mapLatest { sourceNames ->
            getSources().filter { it.name in sourceNames }
        }
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