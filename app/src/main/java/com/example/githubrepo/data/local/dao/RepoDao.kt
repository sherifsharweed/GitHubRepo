package com.example.githubrepo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepo.data.models.GitRepoDetails

@Dao
interface RepoDao {
    @Query("SELECT * FROM GitRepoDetails")
    fun getAll(): List<GitRepoDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GitRepoDetails>)

    @Query("DELETE FROM GitRepoDetails")
    suspend fun deleteAll()
}