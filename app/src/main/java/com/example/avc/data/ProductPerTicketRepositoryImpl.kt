package com.example.avc.data

import android.util.Log
import com.example.avc.database.dao.ProductPerTicketDAO
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.domain.repositories.ProductPerTicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProductPerTicketRepositoryImpl(
    private val productPerTicketDAO: ProductPerTicketDAO
) : ProductPerTicketRepository {
    override suspend fun insertProductsToTicket(products: List<ProductPerTicketEntity>) {
        try {
            productPerTicketDAO.insertProductsToTicket(products)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir el producto al ticket correspondiente")
        }
    }

    override fun getAllProductsPerTicket(): Flow<List<ProductPerTicketEntity>> = flow {
        try {
            emitAll(productPerTicketDAO.getAllProductsPerTicket())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los productos de los tickets")
        }
    }
}