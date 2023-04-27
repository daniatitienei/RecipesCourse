package com.ad_coding.recipe_information_ui

sealed interface RecipeInformationEvent {
    object NavigateBack : RecipeInformationEvent
    object ToggleIngredients : RecipeInformationEvent
}