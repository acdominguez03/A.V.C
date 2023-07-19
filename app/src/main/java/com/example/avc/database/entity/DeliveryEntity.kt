package com.example.avc.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "delivery_table",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["product_id"]
        )
    ]
)
data class DeliveryEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo(name = "status") val status: DeliveryStatus
)

enum class DeliveryStatus(value: String) {
    NO_PAID("No pagado"),
    PAID("Pagado"),
    INVITATION("Invitación")
}
