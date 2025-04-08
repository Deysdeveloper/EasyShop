package com.example.easyshop.Model

data class ProductModel(
    val id: String="",
    val title: String="",
    val description: String="",
    val price: String="",
    val actualprice: String="",
    val category: String="",
    val images: List<String> = emptyList(),
)

