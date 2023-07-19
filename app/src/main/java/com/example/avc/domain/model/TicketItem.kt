package com.example.avc.domain.model

data class TicketItem(
    val productId: Long,
    val productName: String,
    val image: String,
    var amount: Int
)
