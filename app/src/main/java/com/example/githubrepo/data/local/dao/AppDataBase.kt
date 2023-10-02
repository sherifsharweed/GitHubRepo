package com.example.githubrepo.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubrepo.data.models.GitRepoDetails

@Database(entities = [GitRepoDetails::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}