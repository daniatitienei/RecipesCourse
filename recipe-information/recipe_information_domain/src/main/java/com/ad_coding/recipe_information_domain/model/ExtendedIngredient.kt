package com.ad_coding.recipe_information_domain.model

data class ExtendedIngredient(
    val amount: Double = 0.0,
    val id: Int = 0,
    val image: String = "",
    val originalName: String = "",
    val unit: String = ""
)
