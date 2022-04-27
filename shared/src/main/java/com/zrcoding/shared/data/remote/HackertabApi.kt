package com.zrcoding.shared.data.remote

import com.zrcoding.shared.data.remote.dtos.HackerNewsDto
import retrofit2.Response
import retrofit2.http.GET

interface HackertabApi {
    @GET("hackernews.json?dl=1")
    suspend fun fetchHackerNewsPosts() : Response<List<HackerNewsDto>>
}