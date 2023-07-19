package com.example.avc.domain.usecase

import com.example.avc.database.entity.TicketEntity
import com.example.avc.domain.repositories.TicketRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TicketUseCase : KoinComponent {
    private val repository: TicketRepository by inject()

    fun getAllTickets(): Flow<List<TicketEntity>> = repository.getAllTickets()
}