package com.example.githubrepo.data.local

import com.example.githubrepo.data.local.dao.AppDataBase
import com.example.githubrepo.data.models.GitRepoDetails
import javax.inject.Inject

class ReposLocalDataSource @Inject constructor(private val appDataBase: AppDataBase) :
    LocalDataSource {
    override suspend fun getAll(): List<GitRepoDetails> {
        return appDataBase.repoDao().getAll()
    }

    override suspend fun insertAll(repos: List<GitRepoDetails>) {
        appDataBase.repoDao().insertAll(repos)
    }

    override suspend fun deleteAllRepos() {
        appDataBase.repoDao().deleteAll()
    }
}