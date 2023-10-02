package com.example.githubrepo.di

import com.example.githubrepo.data.local.LocalDataSource
import com.example.githubrepo.data.local.ReposLocalDataSource
import com.example.githubrepo.data.local.dao.AppDataBase
import com.example.githubrepo.data.remote.RemoteDataSource
import com.example.githubrepo.data.remote.ReposRemoteDataSource
import com.example.githubrepo.data.remote.network.ReposApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSource {

    @Provides
    fun provideRemoteDataSource(
        reposApiService: ReposApiService,
    ): RemoteDataSource {
        return ReposRemoteDataSource(reposApiService)
    }

    @Provides
    fun provideLocalDataSource(
        appDataBase: AppDataBase,
    ): LocalDataSource {
        return ReposLocalDataSource(appDataBase)
    }
}