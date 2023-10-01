package com.example.githubrepo.data.remote.network

import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApiService {

    @GET(ALL_REPOS)
    suspend fun getAllRepos(): Response<List<RepoResponse>>

    @GET(SINGLE_REPO_INFO)
    suspend fun getSingleRepoInfo(@Path("repo_name")repoName: String,@Path("author_name") authorName:String): Response<SingleRepoInfo>
}