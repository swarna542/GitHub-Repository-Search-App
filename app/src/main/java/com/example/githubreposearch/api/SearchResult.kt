package com.example.githubreposearch.api

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("items")
    val items: List<Repository>
) : List<com.example.githubreposearch.model.Repository> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: com.example.githubreposearch.model.Repository): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<com.example.githubreposearch.model.Repository>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): com.example.githubreposearch.model.Repository {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: com.example.githubreposearch.model.Repository): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<com.example.githubreposearch.model.Repository> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: com.example.githubreposearch.model.Repository): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<com.example.githubreposearch.model.Repository> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<com.example.githubreposearch.model.Repository> {
        TODO("Not yet implemented")
    }

    override fun subList(
        fromIndex: Int,
        toIndex: Int
    ): List<com.example.githubreposearch.model.Repository> {
        TODO("Not yet implemented")
    }
}

data class Repository(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String
)

data class Owner(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

