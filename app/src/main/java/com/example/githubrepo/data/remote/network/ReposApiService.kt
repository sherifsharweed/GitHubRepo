package com.example.githubrepo.data.remote.network

import com.example.githubrepo.data.models.RepoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ReposApiService {

    @GET(ALL_REPOS)
    suspend fun getAllRepos(): Response<List<RepoResponse>>
}