package com.example.githubreposearch.repository

import com.example.githubreposearch.api.RetrofitInstance

class GitHubRepository {
    suspend fun searchRepositories(query: String) =
        RetrofitInstance.api.searchRepositories(query, perPage, page)
}


