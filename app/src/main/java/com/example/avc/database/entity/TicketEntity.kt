package com.example.avc.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
data class TicketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "date") val date: Long
)