package com.example.githubrepo.data.repos

import com.example.githubrepo.data.models.GitRepoDetails
import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.ui.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface ReposRepo {
    suspend fun getAllRepos(): Flow<DataStatus<List<GitRepoDetails>>>
    suspend fun getSingleRepoInfo(
        repoName: String,
        authorName: String
    ): Flow<DataStatus<SingleRepoInfo>>

    suspend fun getRepoIssues(
        repoName: String,
        authorName: String
    ): Flow<DataStatus<List<IssueResponse>>>

    suspend fun getSearchedRepos(text: String): Flow<DataStatus<List<GitRepoDetails>>>

    suspend fun getReposFromLocal(): Flow<DataStatus<List<GitRepoDetails>>>

    suspend fun insertReposToLocal(repos: List<GitRepoDetails>)
}