package com.ad_coding.recipe_information_domain.model

data class RecipeInformation(
    val id: Int = 0,
    val image: String = "",
    val instructions: String = "",
    val extendedIngredients: List<ExtendedIngredient> = listOf(),
    val summary: String = "",
    val title: String = "",
)
