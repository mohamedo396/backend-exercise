package com.example.productapi.entity

import com.example.productapi.dto.ProductResponse
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "products")
data class Product(
    @Id val sku: String,
    var name: String,
    var description: String,
    var price: Double,
    var stock: Int = 0,
) {
    // Add the toResponse method to the Product class
    fun toResponse(): ProductResponse = ProductResponse(
        sku = this.sku,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock
    )
}
