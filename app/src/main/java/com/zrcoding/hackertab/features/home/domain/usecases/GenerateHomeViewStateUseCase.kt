package com.zrcoding.hackertab.features.home.domain.usecases

import com.zrcoding.hackertab.features.home.data.toFreeCodeCamp
import com.zrcoding.hackertab.features.home.data.toGithubRepo
import com.zrcoding.hackertab.features.home.data.toHackerNews
import com.zrcoding.hackertab.features.home.data.toReddit
import com.zrcoding.hackertab.features.home.domain.models.BaseModel
import com.zrcoding.hackertab.features.home.presentation.CardViewState
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.domain.models.SourceName
import com.zrcoding.shared.domain.models.Topic
import com.zrcoding.shared.domain.repositories.ArticleRepository
import com.zrcoding.shared.domain.repositories.SettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GenerateHomeViewStateUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    private val articleRepository: ArticleRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<CardViewState>> {
        return combine(
            flow = settingRepository.observeSelectedTopics(),
            flow2 = settingRepository.observeSelectedSources(),
            transform = ::Pair
        ).mapLatest { pair ->
            pair.second.mapNotNull { source ->
                when (source.name) {
                    SourceName.GITHUB -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            topics = pair.first,
                            getTags = { githubValues.orEmpty() },
                            call = { articleRepository.getGithubArticles(it) },
                            map = { toGithubRepo() }
                        )
                    )

                    SourceName.HACKER_NEWS -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            topics = pair.first,
                            supportTags = false,
                            getTags = { emptyList() },
                            call = { articleRepository.getHackerNewsArticles() },
                            map = { toHackerNews() }
                        )
                    )

                    SourceName.REDDIT -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            topics = pair.first,
                            getTags = { redditValues },
                            call = { articleRepository.getRedditArticles(it) },
                            map = { toReddit() }
                        )
                    )

                    SourceName.FREE_CODE_CAMP -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            topics = pair.first,
                            getTags = { freecodecampValues },
                            call = { articleRepository.getFreeCodeCampArticles(it) },
                            map = { toFreeCodeCamp() }
                        )
                    )

                    else -> null
                }
            }
        }
    }

    private inline fun <Dto: Any, Model: BaseModel> createCardFlow(
        topics: List<Topic>,
        supportTags: Boolean = true,
        crossinline getTags: Topic.() -> List<String>,
        crossinline call: suspend (String) -> Resource<List<Dto>>,
        crossinline map: Dto.() -> Model,
    ): Flow<CardViewState.State> = flow {
        emit(CardViewState.State.Loading)
        if (supportTags.not()) {
            when (val articles = call("")) {
                is Resource.Success -> emit(CardViewState.State.Success(articles.data.map { it.map() }))
                is Resource.Failure -> emit(CardViewState.State.Error)
            }
            return@flow
        }
        val articles = topics.map { topic ->
            val tags = topic.getTags()
            if (tags.isEmpty()) {
                emptyList()
            } else {
                tags.map { call(it) }
                    .filterIsInstance<Resource.Success<List<Dto>>>()
                    .map { it.data }
                    .flatten()
            }
        }.flatten()

        emit(CardViewState.State.Success(articles.map { it.map() }))
    }
}