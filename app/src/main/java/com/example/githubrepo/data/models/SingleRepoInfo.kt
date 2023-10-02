package com.example.githubrepo.data.models

import com.google.gson.annotations.SerializedName

data class SingleRepoInfo(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("private") var private: Boolean? = null,
    @SerializedName("owner") var owner: Owner? = Owner(),
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("stargazers_count") var stargazersCount: Int? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("forks_count") var forksCount: Int? = null,
)