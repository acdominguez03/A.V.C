package com.example.avc.domain.usecase

import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserUseCase : KoinComponent {

    private val repository: UserRepository by inject()

    fun getAllUsers(): Flow<List<UserEntity>> {
        return repository.getAllUsers()
    }

    suspend fun insertAllUsers() {
        repository.insertAll(
            listOf(
                UserEntity(name = "Angel Pastor"),
                UserEntity(name = "M Carmen Grandio"),
                UserEntity(name = "Esther"),
                UserEntity(name = "Andres"),
                UserEntity(name = "Ramon"),
                UserEntity(name = "Darlene"),
                UserEntity(name = "Geovanny"),
                UserEntity(name = "Sofia"),
                UserEntity(name = "Aaron"),
                UserEntity(name = "Eli"),
                UserEntity(name = "Encarna"),
                UserEntity(name = "Antonio"),
                UserEntity(name = "Isaias"),
                UserEntity(name = "Harold"),
                UserEntity(name = "Charlotte"),
                UserEntity(name = "Jose Daniel"),
                UserEntity(name = "Javito"),
                UserEntity(name = "Lucia"),
                UserEntity(name = "Paula"),
                UserEntity(name = "M Carmen Morales"),
                UserEntity(name = "Carlos"),
                UserEntity(name = "Marcos"),
                UserEntity(name = "Miriam Marin"),
                UserEntity(name = "Maritere"),
                UserEntity(name = "Pepe"),
                UserEntity(name = "Juan"),
                UserEntity(name = "Camilo"),
                UserEntity(name = "Nicole"),
                UserEntity(name = "Mathias"),
                UserEntity(name = "Miriam Vidal"),
                UserEntity(name = "Javier"),
                UserEntity(name = "Leonor"),
                UserEntity(name = "Cindy"),
                UserEntity(name = "Sophie"),
                UserEntity(name = "Donatela"),
                UserEntity(name = "Carmenza"),
                UserEntity(name = "Lucas"),
                UserEntity(name = "Lola"),
                UserEntity(name = "Noemí"),
                UserEntity(name = "Consuelo")
            )
        )
    }
}