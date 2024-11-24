package com.example.productapi.dto

data class ProductResponse (
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
)