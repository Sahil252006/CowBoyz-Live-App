package com.example.cowboyz.data.model

data class CartItem(
    val product: Product,
    var quantity: Int = 1,
    var selectedSize: String = "M"
)