package com.example.productapi.service

import com.example.productapi.dto.ProductRequest
import com.example.productapi.dto.ProductResponse
import com.example.productapi.entity.Product
import com.example.productapi.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun getAll(): MutableList<Product> = productRepository.findAll()

    fun getProductBySku(sku: String): ProductResponse =
        productRepository.findById(sku)
            .map { it.toResponse() }
            .orElseThrow { RuntimeException("Product with SKU $sku not found") }

    fun getProductsBySkus(skus: List<String>): List<ProductResponse> =
        productRepository.findBySkuIn(skus).map { it.toResponse() }

    fun addProduct(request: ProductRequest): ProductResponse {
        val product = Product(
            sku = request.sku,
            name = request.name,
            description = request.description,
            price = request.price,
            stock = request.stock
        )
        return productRepository.save(product).toResponse()
    }

    fun updateProduct(sku: String, updates: Map<String, Any>): ProductResponse {
        val product = productRepository.findById(sku)
            .orElseThrow { RuntimeException("Product with SKU $sku not found") }

        updates["name"]?.let { product.name = it as? String ?: product.name }
        updates["description"]?.let { product.description = it as? String ?: product.description }
        updates["price"]?.let { product.price = it as? Double ?: product.price }
        updates["stock"]?.let { product.stock = it as? Int ?: product.stock }

        return productRepository.save(product).toResponse()
    }
}
