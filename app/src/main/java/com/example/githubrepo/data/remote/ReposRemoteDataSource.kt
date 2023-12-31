package com.example.githubrepo.data.remote

import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.models.SearchResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.data.remote.network.ReposApiService
import retrofit2.Response
import javax.inject.Inject

class ReposRemoteDataSource @Inject constructor(private val reposApiService: ReposApiService) :
    RemoteDataSource {
    override suspend fun getAllRepos(): Response<List<RepoResponse>> {
        return reposApiService.getAllRepos()
    }

    override suspend fun getSingleRepoInfo(
        repoName: String,
        authorName: String
    ): Response<SingleRepoInfo> {
        return reposApiService.getSingleRepoInfo(repoName, authorName)
    }

    override suspend fun getRepoIssues(
        repoName: String,
        authorName: String
    ): Response<List<IssueResponse>> {
        return reposApiService.getRepoIssues(repoName, authorName)
    }

    override suspend fun getSearchedRepos(text: String): Response<SearchResponse> {
        return reposApiService.getSearchedRepos(text)
    }
}