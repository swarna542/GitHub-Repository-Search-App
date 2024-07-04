package com.example.githubreposearch.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): SearchResult
}

