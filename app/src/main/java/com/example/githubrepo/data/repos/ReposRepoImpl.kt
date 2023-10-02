package com.example.githubrepo.data.repos

import com.example.githubrepo.data.local.LocalDataSource
import com.example.githubrepo.data.models.GitRepoDetails
import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.data.models.toGitRepositoryDetails
import com.example.githubrepo.data.remote.RemoteDataSource
import com.example.githubrepo.ui.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReposRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    ReposRepo {
    override suspend fun getAllRepos(): Flow<DataStatus<List<GitRepoDetails>>> {
        return flow {
            emit(DataStatus.loading())
            val response = remoteDataSource.getAllRepos()
            if (response.isSuccessful) {
                response.body()?.toList()?.toGitRepositoryDetails().let {
                    insertReposToLocal(it ?: emptyList())
                    emit(DataStatus.success(it))
                }
            } else {
                emit(DataStatus.error(response.message()))
                emitAll(getReposFromLocal())
            }
        }.catch {
            emit(DataStatus.error(it.message))
            emitAll(getReposFromLocal())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSingleRepoInfo(repoName: String, authorName: String)
            : Flow<DataStatus<SingleRepoInfo>> {
        return flow {
            emit(DataStatus.loading())
            val response = remoteDataSource.getSingleRepoInfo(repoName, authorName)
            if (response.isSuccessful) emit(DataStatus.success(response.body()))
            else emit(DataStatus.error(response.message()))
        }.catch {
            emit(DataStatus.error(it.message))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getRepoIssues(
        repoName: String,
        authorName: String
    ): Flow<DataStatus<List<IssueResponse>>> {
        return flow {
            emit(DataStatus.loading())
            val response = remoteDataSource.getRepoIssues(repoName, authorName)
            if (response.isSuccessful) {
                emit(DataStatus.success(response.body()))
            } else emit(DataStatus.error(response.message()))
        }.catch {
            emit(DataStatus.error(it.message))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSearchedRepos(text: String): Flow<DataStatus<List<GitRepoDetails>>> {
        return flow {
            emit(DataStatus.loading())
            val response = remoteDataSource.getSearchedRepos(text)
            if (response.isSuccessful) emit(DataStatus.success(response.body()?.items?.toGitRepositoryDetails()))
            else emit(DataStatus.error(response.message()))
        }.catch {
            emit(DataStatus.error(it.message))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getReposFromLocal(): Flow<DataStatus<List<GitRepoDetails>>> {
        return flow {
            emit(DataStatus.success(localDataSource.getAll()))
        }
    }

    override suspend fun insertReposToLocal(repos: List<GitRepoDetails>) {
        localDataSource.insertAll(repos)
    }
}