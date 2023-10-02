package com.example.githubrepo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RepoResponse(
    @SerializedName("id") var id: Int? = null,
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

@Entity
data class GitRepoDetails(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val author: String = "",
    val avatarUrl: String = "",
    val stars: Int?,
    val description: String = "",
) : Serializable


fun List<RepoResponse>.toGitRepositoryDetails(): List<GitRepoDetails> {
    return map {
        GitRepoDetails(
            id = it.id ?: 0,
            name = it.name ?: "",
            author = it.owner?.login ?: "",
            avatarUrl = it.owner?.avatarUrl ?: "",
            stars = it.stargazersCount,
            description = it.description ?: "",
        )
    }
}