package com.example.avc.domain.model

import com.example.avc.database.entity.DeliveryStatus
import com.example.avc.database.entity.ProductEntity

data class DeliveryItemModel(
    val product: ProductEntity,
    var quantity: Int,
    var checkBoxStatus: DeliveryStatus,
    var expandDropDown: Boolean
)
