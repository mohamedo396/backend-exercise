package com.example.productapi.controller

import com.example.productapi.dto.ProductResponse
import com.example.productapi.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ProductController::class)
class ProductControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var productService: ProductService

    // Test for getProductsBySkus
    @Test
    fun `should return products for given skus`() {
        val skus = listOf("sku1", "sku2")
        val productResponse1 = ProductResponse("sku1", "Product 1", "Description 1", 100.0, 10)
        val productResponse2 = ProductResponse("sku2", "Product 2", "Description 2", 150.0, 5)

        `when`(productService.getProductsBySkus(skus)).thenReturn(listOf(productResponse1, productResponse2))

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/products")
                .param("skus", "sku1", "sku2")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].sku").value("sku1"))
            .andExpect(jsonPath("$[1].sku").value("sku2"))
            .andExpect(jsonPath("$[0].name").value("Product 1"))
            .andExpect(jsonPath("$[1].name").value("Product 2"))

        verify(productService, times(1)).getProductsBySkus(skus)
    }

    // Test for updateProduct
    @Test
    fun `should update product and return updated product`() {
        val sku = "sku1"
        val updates = mapOf("name" to "Updated Product", "price" to 200.0)
        val updatedProductResponse = ProductResponse("sku1", "Updated Product", "Description", 200.0, 10)

        `when`(productService.updateProduct(sku, updates)).thenReturn(updatedProductResponse)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/product/{sku}", sku)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.sku").value("sku1"))
            .andExpect(jsonPath("$.name").value("Updated Product"))
            .andExpect(jsonPath("$.price").value(200.0))

        verify(productService, times(1)).updateProduct(sku, updates)
    }
}
