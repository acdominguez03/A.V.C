package com.example.avc.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_per_ticket_table",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_product"]
        ),
        ForeignKey(
            entity = TicketEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_ticket"]
        )
    ]
)
data class ProductPerTicketEntity(
    @ColumnInfo(name = "amount") var amount: Int,
    @ColumnInfo(name = "id_product") val productId: Long,
    @ColumnInfo(name = "id_ticket") val ticketId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        set(id) {
            field = id + 1
        }
}
