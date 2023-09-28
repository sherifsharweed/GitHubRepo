package com.example.githubrepo.data.repos

import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.ui.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface ReposRepo {
    suspend fun getAllRepos(): Flow<DataStatus<List<RepoResponse>>>

}