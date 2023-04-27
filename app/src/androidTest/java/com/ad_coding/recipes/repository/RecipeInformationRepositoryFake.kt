package com.ad_coding.recipes.repository

import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_information_domain.model.RecipeInformation
import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository

class RecipeInformationRepositoryFake : RecipeInformationRepository {
    override suspend fun getInfo(id: String): RecipeInformation? =
        RecipeInformation(
            id = 0,
            image = "https://spoonacular.com/recipeImages/716429-556x370.jpg",
            summary = "Random recipe",
            title = "Favorite food"
        )
}