package com.example.avc.domain.model

data class ExpensesItemModel(
    var productId: Long,
    var productName: String,
    var productPrice: Double,
    var quantity: Int
)