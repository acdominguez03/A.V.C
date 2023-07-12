package com.example.avc.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_per_delivery_table",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_product"]
        ),
        ForeignKey(
            entity = DeliveryEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_delivery"]
        )
    ]
)
data class ProductPerDeliveryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "paid") val status: String,
    @ColumnInfo(name = "id_product") val productId: Int,
    @ColumnInfo(name = "id_delivery") val ticketId: Int
)