package com.ad_coding.recipe_list_domain.use_case

import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import kotlinx.coroutines.flow.filter

class GetRecipeList(
    private val repository: RecipeRepository
) {
    fun execute() = repository.getRandomRecipes()
}