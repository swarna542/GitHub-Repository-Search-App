package com.example.githubreposearch.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubreposearch.api.Repository

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<Repository>)

    @Query("SELECT * FROM repository LIMIT 15")
    suspend fun getSavedRepositories(): List<Repository>
}
