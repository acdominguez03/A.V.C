package com.example.avc.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.avc.database.entity.DeliveryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveries(deliveries: List<DeliveryEntity>)

    @Query("SELECT * FROM delivery_table")
    fun getAllDeliveries(): Flow<List<DeliveryEntity>>
}
