package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllProducts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertProduct(productEntity: ProductEntity)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(products: List<ProductEntity>)

}