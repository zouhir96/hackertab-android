package com.zrcoding.hackertab.settings

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.zrcoding.hackertab.settings.data.repositories.SettingRepositoryImpl
import com.zrcoding.hackertab.settings.domain.repositories.SettingRepository
import com.zrcoding.hackertab.settings.presentation.sources.SettingSourcesScreenViewModel
import com.zrcoding.hackertab.settings.presentation.topics.SettingTopicsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val USER_PREFERENCES = "user_preferences"

val settingsModule = module {
    factory {
        val appContext: Context = get()
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    singleOf(::SettingRepositoryImpl) bind SettingRepository::class
    viewModel { SettingSourcesScreenViewModel(get()) }
    viewModel { SettingTopicsScreenViewModel(get()) }
}