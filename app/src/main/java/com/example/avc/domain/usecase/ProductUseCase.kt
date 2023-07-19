package com.example.avc.domain.usecase

import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductUseCase : KoinComponent {

    private val repository: ProductRepository by inject()

    fun getAllProducts(): Flow<List<ProductEntity>> = repository.getAllProducts()
}