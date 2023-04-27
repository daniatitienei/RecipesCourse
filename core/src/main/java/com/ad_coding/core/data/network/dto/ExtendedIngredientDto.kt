package com.ad_coding.core.data.network.dto

data class ExtendedIngredientDto(
    val aisle: String = "",
    val amount: Double = 0.0,
    val consistency: String = "",
    val id: Int = 0,
    val image: String? = null,
    val measures: MeasuresDto = MeasuresDto(),
    val meta: List<String> = listOf(),
    val name: String = "",
    val nameClean: String = "",
    val original: String = "",
    val originalName: String = "",
    val unit: String = ""
)