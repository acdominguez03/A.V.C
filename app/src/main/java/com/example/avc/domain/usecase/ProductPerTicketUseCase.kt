package com.example.avc.domain.usecase

import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.domain.repositories.ProductPerTicketRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductPerTicketUseCase : KoinComponent {
    private val repository: ProductPerTicketRepository by inject()

    fun getAllProductsPerTickets(): Flow<List<ProductPerTicketEntity>> = repository.getAllProductsPerTicket()
}
