package com.example.githubrepo.data.repos

import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.ui.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface ReposRepo {
    suspend fun getAllRepos(): Flow<DataStatus<List<RepoResponse>>>
    suspend fun getSingleRepoInfo(
        repoName: String,
        authorName: String
    ): Flow<DataStatus<SingleRepoInfo>>

    suspend fun getRepoIssues(
        repoName: String,
        authorName: String
    ): Flow<DataStatus<List<IssueResponse>>>

}