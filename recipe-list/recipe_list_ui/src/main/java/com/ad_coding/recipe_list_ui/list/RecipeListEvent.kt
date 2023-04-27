package com.ad_coding.recipe_list_ui.list

import com.ad_coding.recipe_list_domain.model.Recipe

sealed interface RecipeListEvent {
    data class RecipeClick(val recipe: Recipe) : RecipeListEvent
    object Refresh : RecipeListEvent
    object SearchClick : RecipeListEvent
}