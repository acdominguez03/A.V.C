package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.ProductPerTicketEntity
import kotlinx.coroutines.flow.Flow

interface ProductPerTicketRepository {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertProductsToTicket(products: List<ProductPerTicketEntity>)

    fun getAllProductsPerTicket(): Flow<List<ProductPerTicketEntity>>
}