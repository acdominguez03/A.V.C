package com.example.avc.data

import android.util.Log
import com.example.avc.database.dao.TicketDAO
import com.example.avc.database.entity.TicketEntity
import com.example.avc.domain.repositories.TicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.io.IOException

class TicketRepositoryImpl(
    private val ticketDAO: TicketDAO
) : TicketRepository {
    override suspend fun insertTicket(ticketEntity: TicketEntity): Long? {
        return try {
            ticketDAO.insert(ticketEntity)
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al añadir el ticket a la BBDD").toLong()
        }
    }

    override fun getAllTickets(): Flow<List<TicketEntity>> = flow {
        try {
            emitAll(ticketDAO.getAllTickets())
        } catch (e: IOException) {
            Log.d("MY_TAG", "Error al obtener los tickets de la BBDD")
        }
    }
}
