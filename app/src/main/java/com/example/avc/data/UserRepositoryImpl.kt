package com.example.avc.data

import android.util.Log
import com.example.avc.database.dao.UserDAO
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.ExpensesItemModel
import com.example.avc.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserRepositoryImpl(
    private val userDAO: UserDAO
) : UserRepository {
    override suspend fun insertAll(users: List<UserEntity>) {
        try {
            userDAO.insertAll(users = users)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir los usuarios a la BBDD")
        }
    }

    override fun getAllUsers(): Flow<List<UserEntity>> = flow {
        try {
            emitAll(userDAO.getAllUsers())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los usuarios de la BBDD")
        }
    }

    override fun getUsersWithExpenses(): Flow<List<UserEntity>> = flow {
        try {
            emitAll(userDAO.getUsersWithExpenses())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los usuarios con gastos de la BBDD")
        }
    }

    override fun getUserExpenses(userId: Long): Flow<List<ExpensesItemModel>> = flow {
        try {
            emitAll(userDAO.getUserExpenses(userId))
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los gastos de un usuario de la BBDD")
        }
    }

}