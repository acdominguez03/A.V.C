package com.example.avc.data

import android.util.Log
import com.example.avc.database.dao.DeliveryDAO
import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.domain.repositories.DeliveryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
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

    override fun getAllDeliveries(): Flow<List<DeliveryEntity>> = flow {
        try {
            emitAll(deliveryDAO.getAllDeliveries())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener las entregas de la BBDD")
        }
    }

}