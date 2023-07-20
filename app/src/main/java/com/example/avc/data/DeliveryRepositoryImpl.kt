package com.example.avc.data

import android.util.Log
import com.example.avc.database.dao.DeliveryDAO
import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.domain.repositories.DeliveryRepository
import java.io.IOException

class DeliveryRepositoryImpl(
    private val deliveryDAO: DeliveryDAO
) : DeliveryRepository {
    override suspend fun insertDeliveries(deliveries: List<DeliveryEntity>) {
        try {
            deliveryDAO.insertDeliveries(deliveries = deliveries)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir las entregas")
        }
    }

}