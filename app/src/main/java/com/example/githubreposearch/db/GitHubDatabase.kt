package com.example.githubreposearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubreposearch.api.Repository
import com.example.githubreposearch.repository.RepositoryDao

@Database(entities = [Repository::class], version = 1)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: GitHubDatabase? = null

        fun getDatabase(context: Context): GitHubDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GitHubDatabase::class.java,
                    "github_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
