package com.example.avc.domain.usecase

import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.repositories.DeliveryRepository
import com.example.avc.domain.repositories.ProductRepository
import com.example.avc.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddDeliveryUseCase : KoinComponent {
    private val productRepository: ProductRepository by inject()
    private val userRepository: UserRepository by inject()
    private val deliveryRepository: DeliveryRepository by inject()

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productRepository.getAllProducts()
    }

    fun getAllUsers(): Flow<List<UserEntity>> {
        return userRepository.getAllUsers()
    }

    suspend fun insertDelivery(deliveryEntity: DeliveryEntity) {
        return deliveryRepository.insertDelivery(deliveryEntity)
    }

    suspend fun insertAllUsers() {
        userRepository.insertAll(
            listOf(
                UserEntity(name = "Ángel Pastor"),
                UserEntity(name = "Esther"),
                UserEntity(name = "Aaron"),
                UserEntity(name = "Camilo"),
                UserEntity(name = "Carlos"),
                UserEntity(name = "Darlene"),
                UserEntity(name = "Eli"),
                UserEntity(name = "Encarna"),
                UserEntity(name = "Antonio"),
                UserEntity(name = "Geovanny"),
                UserEntity(name = "Harold"),
                UserEntity(name = "Isaias"),
                UserEntity(name = "Jose Daniel"),
                UserEntity(name = "Javito"),
                UserEntity(name = "Lucia"),
                UserEntity(name = "M Carmen Morales"),
                UserEntity(name = "Marcos"),
                UserEntity(name = "Miriam Marin"),
                UserEntity(name = "M Carmen Grandio"),
                UserEntity(name = "Maritere"),
                UserEntity(name = "Pepe"),
                UserEntity(name = "Nicole"),
                UserEntity(name = "Paula"),
                UserEntity(name = "Consuelo"),
                UserEntity(name = "Juan"),
                UserEntity(name = "Lola"),
                UserEntity(name = "Miriam Vidal"),
                UserEntity(name = "Javier"),
                UserEntity(name = "Sofia"),
                UserEntity(name = "Leonor"),
                UserEntity(name = "Andres"),
                UserEntity(name = "Ramon")
            )
        )
    }
}