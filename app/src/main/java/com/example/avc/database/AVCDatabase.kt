package com.example.avc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.avc.database.dao.*
import com.example.avc.database.entity.*

@Database(
    entities = arrayOf(
        ProductEntity::class,
        TicketEntity::class,
        UserEntity::class,
        DeliveryEntity::class,
        ProductPerTicketEntity::class
    ),
    exportSchema = false,
    version = 1
)
abstract class AVCDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
    abstract fun ticketDao(): TicketDAO
    abstract fun userDao(): UserDAO
    abstract fun deliveryDao(): DeliveryDAO
    abstract fun productPerTicketDao(): ProductPerTicketDAO
}
