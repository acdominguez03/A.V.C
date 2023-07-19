package com.example.avc.domain.usecase

import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.database.entity.TicketEntity
import com.example.avc.domain.repositories.ProductPerTicketRepository
import com.example.avc.domain.repositories.ProductRepository
import com.example.avc.domain.repositories.TicketRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddTicketsUseCase : KoinComponent {
    private val productRepository: ProductRepository by inject()
    private val ticketRepository: TicketRepository by inject()
    private val productPerTicketRepository: ProductPerTicketRepository by inject()

    suspend fun addNewTicket(ticketEntity: TicketEntity): Long? = ticketRepository.insertTicket(ticketEntity)

    suspend fun updateProducts(products: List<ProductEntity>) {
        productRepository.updateProducts(products)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productRepository.getAllProducts()
    }

    suspend fun addProductToTickets(products: List<ProductPerTicketEntity>) {
        return productPerTicketRepository.insertProductsToTicket(products = products)
    }
}