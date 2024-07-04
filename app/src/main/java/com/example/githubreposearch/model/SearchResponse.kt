package com.example.githubreposearch.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items") val repositories: List<Repository>
)
