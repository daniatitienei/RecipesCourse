package com.ad_coding.core.data.network.dto

data class ProductMatcheDto(
    val averageRating: Double = 0.0,
    val description: String = "",
    val id: Int = 0,
    val imageUrl: String = "",
    val link: String = "",
    val price: String = "",
    val ratingCount: Double = 0.0,
    val score: Double = 0.0,
    val title: String = ""
)