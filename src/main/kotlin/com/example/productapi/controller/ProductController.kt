package com.example.productapi.controller

import com.example.productapi.dto.ProductRequest
import com.example.productapi.dto.ProductResponse
import com.example.productapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/product/{sku}")
    fun getProductBySku(@PathVariable sku: String): ProductResponse =
        productService.getProductBySku(sku)

    @GetMapping("/allproducts")
    fun getAllProducts(): List<ProductResponse> =
        productService.getAll().map { it.toResponse() } // Fixed return type

    @GetMapping("/products")
    fun getProductsBySkus(@RequestParam skus: List<String>): List<ProductResponse> =
        productService.getProductsBySkus(skus)

    @PostMapping("/product")
    fun addProduct(@RequestBody request: ProductRequest): ProductResponse =
        productService.addProduct(request)

    @PatchMapping("/product/{sku}")
    fun updateProduct(
        @PathVariable sku: String,
        @RequestBody updates: Map<String, Any>
    ): ProductResponse = productService.updateProduct(sku, updates)
}
