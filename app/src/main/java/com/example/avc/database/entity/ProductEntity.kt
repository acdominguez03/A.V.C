package com.example.avc.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val name: String,

    val price: Double,

    val image: String,

    val amount: Int
)
