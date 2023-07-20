package com.example.avc.domain.usecase

import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.domain.repositories.DeliveryRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeliveryUseCase : KoinComponent {

    private val repository: DeliveryRepository by inject()

    suspend fun insertDeliveries(deliveries: List<DeliveryEntity>) {
        return repository.insertDeliveries(deliveries = deliveries)
    }
}