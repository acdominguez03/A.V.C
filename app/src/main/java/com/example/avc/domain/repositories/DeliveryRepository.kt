package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.DeliveryEntity
import kotlinx.coroutines.flow.Flow

interface DeliveryRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDeliveries(deliveries: List<DeliveryEntity>)

    fun getAllDeliveries(): Flow<List<DeliveryEntity>>

}