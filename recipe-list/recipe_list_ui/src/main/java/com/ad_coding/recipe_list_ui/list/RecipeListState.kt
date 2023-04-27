package com.ad_coding.recipe_list_ui.list

import com.ad_coding.recipe_list_domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList()
)
