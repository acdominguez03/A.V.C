package com.example.avc.domain.repositories

import androidx.annotation.WorkerThread
import com.example.avc.database.entity.TicketEntity
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTicket(ticketEntity: TicketEntity): Long?

    fun getAllTickets(): Flow<List<TicketEntity>>

}