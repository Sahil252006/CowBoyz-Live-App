package com.example.cowboyz.data.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: String,
    val subCategory: String,
    val description: String,
    val imageRes: Int,
    val sizes: List<String> = listOf("S", "M", "L", "XL")
)