package com.zrcoding.hackertab.home

import com.zrcoding.hackertab.home.data.repositories.ArticleRepositoryImpl
import com.zrcoding.hackertab.home.domain.repositories.ArticleRepository
import com.zrcoding.hackertab.home.domain.usecases.GenerateHomeViewStateUseCase
import com.zrcoding.hackertab.home.presentation.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    singleOf(::ArticleRepositoryImpl) bind ArticleRepository::class
    factory { GenerateHomeViewStateUseCase(settingRepository = get(), articleRepository = get()) }
    viewModel { HomeScreenViewModel(generateHomeViewStateUseCase = get()) }
}