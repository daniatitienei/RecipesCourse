package com.ad_coding.recipe_list_domain.repository

import com.ad_coding.recipe_list_domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRandomRecipes(): Flow<List<Recipe>>

    suspend fun searchRecipes(query: String): List<Recipe>
}