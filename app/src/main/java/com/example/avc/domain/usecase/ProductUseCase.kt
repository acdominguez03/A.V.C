package com.example.avc.domain.usecase

import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductUseCase : KoinComponent {

    private val repository: ProductRepository by inject()

    fun getAllProducts(): Flow<List<ProductEntity>> = repository.getAllProducts()

    suspend fun insert(productEntity: ProductEntity) {
        repository.insertProduct(productEntity)
    }

    suspend fun insertAll(products: List<ProductEntity>) {
        repository.insertAll(products)
    }

    suspend fun deleteAll() {
        repository.deleteAllProducts()
    }

    suspend fun updateProducts(products: List<ProductEntity>) {
        repository.updateProducts(products)
    }

    suspend fun getProfits(): Flow<Double> = repository.getProfits()
}