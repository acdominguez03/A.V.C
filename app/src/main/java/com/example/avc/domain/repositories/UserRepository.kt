package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.ExpensesItemModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(users: List<UserEntity>)
    fun getAllUsers(): Flow<List<UserEntity>>
    fun getUsersWithExpenses(): Flow<List<UserEntity>>

    fun getUserExpenses(userId: Long): Flow<List<ExpensesItemModel>>
}
