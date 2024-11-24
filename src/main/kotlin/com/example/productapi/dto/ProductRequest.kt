package com.example.productapi.dto

data class ProductRequest(
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int
)
