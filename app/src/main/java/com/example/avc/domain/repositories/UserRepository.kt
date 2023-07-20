package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(users: List<UserEntity>)
    fun getAllUsers(): Flow<List<UserEntity>>

}
