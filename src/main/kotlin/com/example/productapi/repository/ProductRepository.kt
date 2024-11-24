package com.example.productapi.repository

import com.example.productapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, String> {
    fun findBySkuIn(skus:List<String>):List<Product>
}