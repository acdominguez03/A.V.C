package com.example.avc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.avc.database.dao.ProductDAO
import com.example.avc.database.entity.*

@Database(
    entities = arrayOf(
        ProductEntity::class,
        /*UserEntity::class,
        DeliveryEntity::class,
        ProductPerDeliveryEntity::class,
        ProductPerTicketEntity::class,
        TicketEntity::class*/
    ),
    version = 1,
    exportSchema = false
)
abstract class AVCDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

}