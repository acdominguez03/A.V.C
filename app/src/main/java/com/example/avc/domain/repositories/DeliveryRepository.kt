package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.DeliveryEntity

interface DeliveryRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDelivery(deliveryEntity: DeliveryEntity)
}