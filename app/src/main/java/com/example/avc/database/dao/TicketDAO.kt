package com.example.avc.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avc.database.entity.TicketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDAO {

    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insert(ticketEntity: TicketEntity): Long

    @Query("SELECT * FROM ticket_table")
    fun getAllTickets(): Flow<List<TicketEntity>>

}