package com.example.githubrepo.data.remote

import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.models.SearchResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getAllRepos(): Response<List<RepoResponse>>

    suspend fun getSingleRepoInfo(repoName: String, authorName: String): Response<SingleRepoInfo>

    suspend fun getRepoIssues(repoName: String, authorName: String): Response<List<IssueResponse>>

    suspend fun getSearchedRepos(text: String): Response<SearchResponse>

}