package com.zrcoding.shared.data.remote

import com.zrcoding.shared.data.remote.dtos.FreeCodeCampDto
import com.zrcoding.shared.data.remote.dtos.HackerNewsDto
import com.zrcoding.shared.data.remote.dtos.RedditDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface HackertabApi {
    @GET("data/hackernews.json?dl=1")
    suspend fun fetchHackerNewsPosts(): Response<List<HackerNewsDto>>

    @GET("reddit/javascript/top/.json?t=week")
    suspend fun fetchRedditPosts(): Response<RedditDto>

    @GET
    suspend fun fetchFreeCodeCampPosts(@Url url: String): Response<List<FreeCodeCampDto>>
}