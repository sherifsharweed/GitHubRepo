package com.example.githubrepo.data.remote

import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.data.remote.network.ReposApiService
import retrofit2.Response
import javax.inject.Inject

class ReposRemoteDataSource @Inject constructor(private val reposApiService: ReposApiService) :
    RemoteDataSource {
    override suspend fun getAllRepos(): Response<List<RepoResponse>> {
        return reposApiService.getAllRepos()
    }

    override suspend fun getSingleRepoInfo(repoName: String, authorName:String): Response<SingleRepoInfo> {
        return reposApiService.getSingleRepoInfo(repoName,authorName)
    }
}