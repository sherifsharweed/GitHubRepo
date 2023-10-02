package com.example.githubrepo.data.models

import com.google.gson.annotations.SerializedName


data class RepoResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("owner") var owner: Owner? = Owner(),
    @SerializedName("description") var description: String? = null,
    @SerializedName("stargazers_count") var stargazersCount: Int? = null,

    )

data class Owner(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
)

data class SearchResponse(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: ArrayList<RepoResponse> = arrayListOf()

)