package com.example.avc.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avc.database.entity.ProductPerTicketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductPerTicketDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProductsToTicket(products: List<ProductPerTicketEntity>)

    @Query("SELECT * FROM product_per_ticket_table")
    fun getAllProductsPerTicket(): Flow<List<ProductPerTicketEntity>>
}