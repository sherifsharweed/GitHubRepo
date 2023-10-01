package com.example.githubrepo.data.models

import com.google.gson.annotations.SerializedName


data class RepoResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("private") var private: Boolean? = null,
    @SerializedName("owner") var owner: Owner? = Owner(),
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("fork") var fork: Boolean? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("forks_url") var forksUrl: String? = null,
    @SerializedName("keys_url") var keysUrl: String? = null,
    @SerializedName("collaborators_url") var collaboratorsUrl: String? = null,
    @SerializedName("teams_url") var teamsUrl: String? = null,
    @SerializedName("hooks_url") var hooksUrl: String? = null,
    @SerializedName("issue_events_url") var issueEventsUrl: String? = null,
    @SerializedName("events_url") var eventsUrl: String? = null,
    @SerializedName("assignees_url") var assigneesUrl: String? = null,
    @SerializedName("branches_url") var branchesUrl: String? = null,
    @SerializedName("tags_url") var tagsUrl: String? = null,
    @SerializedName("blobs_url") var blobsUrl: String? = null,
    @SerializedName("git_tags_url") var gitTagsUrl: String? = null,
    @SerializedName("git_refs_url") var gitRefsUrl: String? = null,
    @SerializedName("trees_url") var treesUrl: String? = null,
    @SerializedName("statuses_url") var statusesUrl: String? = null,
    @SerializedName("languages_url") var languagesUrl: String? = null,
    @SerializedName("stargazers_url") var stargazersUrl: String? = null,
)

data class Owner(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
)