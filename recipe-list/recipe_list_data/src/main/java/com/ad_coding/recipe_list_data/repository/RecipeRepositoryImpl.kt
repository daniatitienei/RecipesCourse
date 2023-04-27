package com.ad_coding.recipe_list_data.repository

import android.content.Context
import com.ad_coding.core.data.network.Secrets
import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_list_data.mapper.toRecipe
import com.ad_coding.recipe_list_domain.model.Recipe
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.ad_coding.recipes.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl(
    private val api: SpoonacularApi,
) : RecipeRepository {

    override fun getRandomRecipes(): Flow<List<Recipe>> = flow {
        val recipeList =
            api.getRandomRecipes(number = 1)
                .recipes
                .map { dto ->
                    dto.toRecipe()
                }

        emit(recipeList)
    }

    override suspend fun searchRecipes(query: String): List<Recipe> =
        api.searchRecipes(query = query)
            .results
            .map { dto ->
                dto.toRecipe()
            }
}