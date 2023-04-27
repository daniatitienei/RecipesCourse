package com.ad_coding.recipe_information_ui

import com.ad_coding.recipe_information_domain.model.RecipeInformation

data class RecipeInformationState(
    val recipe: RecipeInformation? = null,
    val isLoading: Boolean = false,
    val areIngredientsVisible: Boolean = true
)
