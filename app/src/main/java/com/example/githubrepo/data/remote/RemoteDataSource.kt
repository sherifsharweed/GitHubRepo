package com.example.githubrepo.data.remote

import com.example.githubrepo.data.models.RepoResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getAllRepos(): Response<List<RepoResponse>>
}