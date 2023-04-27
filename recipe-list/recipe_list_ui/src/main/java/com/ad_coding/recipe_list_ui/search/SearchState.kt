package com.ad_coding.recipe_list_ui.search

import com.ad_coding.recipe_list_domain.model.Recipe

data class SearchState(
    val isLoading: Boolean = false,
    val query: String = "",
    val recipeList: List<Recipe> = emptyList()
)
