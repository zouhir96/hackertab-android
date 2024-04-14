package com.zrcoding.hackertab.features.home.domain.usecases

import com.zrcoding.hackertab.features.home.data.toConference
import com.zrcoding.hackertab.features.home.data.toDevto
import com.zrcoding.hackertab.features.home.data.toFreeCodeCamp
import com.zrcoding.hackertab.features.home.data.toGithubRepo
import com.zrcoding.hackertab.features.home.data.toHackerNews
import com.zrcoding.hackertab.features.home.data.toHashnode
import com.zrcoding.hackertab.features.home.data.toIndieHackers
import com.zrcoding.hackertab.features.home.data.toLobster
import com.zrcoding.hackertab.features.home.data.toMedium
import com.zrcoding.hackertab.features.home.data.toProductHunt
import com.zrcoding.hackertab.features.home.data.toReddit
import com.zrcoding.hackertab.features.home.domain.models.BaseModel
import com.zrcoding.hackertab.features.home.presentation.CardViewState
import com.zrcoding.shared.domain.models.NetworkErrors
import com.zrcoding.shared.domain.models.Resource
import com.zrcoding.shared.domain.repositories.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GenerateHomeViewStateUseCase @Inject constructor(
    private val settingRepository: com.zrcoding.hackertab.settings.domain.repositories.SettingRepository,
    private val articleRepository: ArticleRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<List<CardViewState>> {
        return combine(
            flow = settingRepository.observeSelectedTopics(),
            flow2 = settingRepository.observeSelectedSources(),
            transform = ::Pair
        ).mapLatest { pair ->
            val topics = pair.first.ifEmpty { listOf(com.zrcoding.hackertab.settings.domain.models.Topic.global) }
            pair.second.map { source ->
                when (source.name) {
                    com.zrcoding.hackertab.settings.domain.models.SourceName.GITHUB -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { githubValues.orEmpty() },
                            getArticles = { articleRepository.getGithubRepositories(it) },
                            toModel = { toGithubRepo() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.HACKER_NEWS -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            supportTags = false,
                            getTags = { emptyList() },
                            getArticles = { articleRepository.getHackerNewsArticles() },
                            toModel = { toHackerNews() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.REDDIT -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { redditValues },
                            getArticles = { articleRepository.getRedditArticles(it) },
                            toModel = { toReddit() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.FREE_CODE_CAMP -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { freecodecampValues },
                            getArticles = { articleRepository.getFreeCodeCampArticles(it) },
                            toModel = { toFreeCodeCamp() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.CONFERENCES -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { confsValues.orEmpty() },
                            getArticles = { articleRepository.getConferences(it) },
                            toModel = { toConference() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.DEVTO -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { devtoValues },
                            getArticles = { articleRepository.getDevtoArticles(it) },
                            toModel = { toDevto() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.HASH_NODE -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { hashnodeValues },
                            getArticles = { articleRepository.getHashnodeArticles(it) },
                            toModel = { toHashnode() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.PRODUCTHUNT -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            supportTags = false,
                            getTags = { emptyList() },
                            getArticles = { articleRepository.getProductHuntProducts() },
                            toModel = { toProductHunt() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.INDIE_HACKERS -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            supportTags = false,
                            getTags = { emptyList() },
                            getArticles = { articleRepository.getIndieHackersArticles() },
                            toModel = { toIndieHackers() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.LOBSTERS -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            supportTags = false,
                            getTags = { emptyList() },
                            getArticles = { articleRepository.getLobstersArticles() },
                            toModel = { toLobster() }
                        )
                    )

                    com.zrcoding.hackertab.settings.domain.models.SourceName.MEDIUM -> CardViewState(
                        source = source,
                        state = createCardFlow(
                            source = source,
                            topics = topics,
                            getTags = { mediumValues },
                            getArticles = { articleRepository.getMediumArticles(it) },
                            toModel = { toMedium() }
                        )
                    )
                }
            }
        }
    }

    private inline fun <Dto : Any, Model : BaseModel> createCardFlow(
        source: com.zrcoding.hackertab.settings.domain.models.Source,
        topics: List<com.zrcoding.hackertab.settings.domain.models.Topic>,
        supportTags: Boolean = true,
        crossinline getTags: com.zrcoding.hackertab.settings.domain.models.Topic.() -> List<String>,
        crossinline getArticles: suspend (String) -> Resource<List<Dto>, NetworkErrors>,
        crossinline toModel: Dto.() -> Model,
    ): Flow<CardViewState.State> = flow {
        emit(CardViewState.State.Loading)
        if (supportTags.not()) {
            when (val result = getArticles("")) {
                is Resource.Success -> emit(CardViewState.State.Success(result.data.map { it.toModel() }))
                is Resource.Failure -> emit(result.error.toStateError(source.name.value))
            }
            return@flow
        }
        val articles = mutableListOf<Dto>()
        var noInternet = false
        var failedToLoad = false
        topics.forEach { topic ->
            topic.getTags().forEach {
                when (val result = getArticles(it)) {
                    is Resource.Success -> articles.addAll(result.data)
                    is Resource.Failure -> when(result.error){
                        NetworkErrors.NO_INTERNET,
                        NetworkErrors.REQUEST_TIMEOUT -> noInternet = true
                        NetworkErrors.UNKNOWN -> failedToLoad = true
                    }
                }
            }
        }
        if (articles.isEmpty() && failedToLoad) {
            emit(CardViewState.State.Error("Failed to load ${source.name.value} articles"))
        } else if (articles.isEmpty() && noInternet) {
            emit(CardViewState.State.VerifyConnectionAndRefresh)
        } else emit(CardViewState.State.Success(articles.map { it.toModel() }))
    }

    private fun NetworkErrors.toStateError(sourceName: String): CardViewState.State = when (this) {
        NetworkErrors.NO_INTERNET,
        NetworkErrors.REQUEST_TIMEOUT -> CardViewState.State.VerifyConnectionAndRefresh

        NetworkErrors.UNKNOWN -> CardViewState.State.Error("Failed to load $sourceName articles")
    }
}