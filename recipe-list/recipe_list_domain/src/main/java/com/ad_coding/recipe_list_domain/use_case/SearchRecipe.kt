package com.ad_coding.recipe_list_domain.use_case

import com.ad_coding.recipe_list_domain.model.Recipe
import com.ad_coding.recipe_list_domain.repository.RecipeRepository

class SearchRecipe(
    private val repository: RecipeRepository
) {
    suspend fun execute(query: String): List<Recipe> {
        return if (query.isBlank()) {
            emptyList()
        } else {
            repository.searchRecipes(query)
        }
    }
}