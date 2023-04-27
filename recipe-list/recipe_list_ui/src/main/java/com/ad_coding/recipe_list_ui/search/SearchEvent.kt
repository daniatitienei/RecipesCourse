package com.ad_coding.recipe_list_ui.search

import com.ad_coding.recipe_list_domain.model.Recipe

sealed interface SearchEvent {
    data class QueryValueChange(val newValue: String) : SearchEvent
    object ClearQuery : SearchEvent
    object Search : SearchEvent
    data class RecipeClick(val recipe: Recipe) : SearchEvent
    object NavigateBack : SearchEvent
}