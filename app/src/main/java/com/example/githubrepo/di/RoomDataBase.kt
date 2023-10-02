package com.example.githubrepo.di

import android.content.Context
import androidx.room.Room
import com.example.githubrepo.data.local.dao.AppDataBase
import com.example.githubrepo.data.local.dao.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDataBase {
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "gitHubRepos.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTripDao(database: AppDataBase): RepoDao {
        return database.repoDao()
    }
}