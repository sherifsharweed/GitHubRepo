package com.example.githubrepo.di

import com.example.githubrepo.data.local.LocalDataSource
import com.example.githubrepo.data.remote.RemoteDataSource
import com.example.githubrepo.data.repos.ReposRepo
import com.example.githubrepo.data.repos.ReposRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Repos {
    @Provides
    fun provideNewsRepo(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ReposRepo {
        return ReposRepoImpl(remoteDataSource,localDataSource)
    }
}