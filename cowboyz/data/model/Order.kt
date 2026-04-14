package com.example.cowboyz.data.model

data class Order(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userPhone: String = "",
    val items: List<CartItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val paymentMethod: String = "",
    val address: Address = Address(),
    val date: String = "",
    val status: String = "Placed"
)