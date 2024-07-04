package com.example.githubreposearch.model

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: Owner,
    val stargazers_count: Int,
    val forks_count: Int,
    val html_url: String
)

