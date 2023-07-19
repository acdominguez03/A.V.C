package com.example.avc.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    val name: String,

    val price: Double,

    val image: String,

    var amount: Int
)
