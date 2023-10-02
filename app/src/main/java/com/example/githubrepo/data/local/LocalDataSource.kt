package com.example.githubrepo.data.local

import com.example.githubrepo.data.models.GitRepoDetails

interface LocalDataSource {
    suspend fun getAll(): List<GitRepoDetails>
    suspend fun insertAll(repos: List<GitRepoDetails>)
    suspend fun deleteAllRepos()

}