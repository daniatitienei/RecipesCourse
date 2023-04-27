package com.ad_coding.recipes.repository

import com.ad_coding.recipe_list_domain.model.Recipe
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryFake : RecipeRepository {
    override fun getRandomRecipes(): Flow<List<Recipe>> = flow {
        val recipes = listOf(
            Recipe(
                id = 0,
                title = "Recipe 1"
            ),
            Recipe(
                id = 1,
                title = "Recipe 2"
            )
        )

        emit(recipes)
    }

    override suspend fun searchRecipes(query: String): List<Recipe> =
        listOf(
            Recipe(
                id = 0,
                title = "Pizza 1"
            ),
            Recipe(
                id = 1,
                title = "Pizza 2"
            )
        )
}