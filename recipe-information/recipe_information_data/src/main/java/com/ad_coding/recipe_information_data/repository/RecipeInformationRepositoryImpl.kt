package com.ad_coding.recipe_information_data.repository

import com.ad_coding.core.data.network.Secrets
import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_information_data.mapper.toRecipeInformation
import com.ad_coding.recipe_information_domain.model.RecipeInformation
import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository

class RecipeInformationRepositoryImpl(
    private val api: SpoonacularApi
) : RecipeInformationRepository {
    override suspend fun getInfo(id: String): RecipeInformation? =
        api.getRecipeInformation(id = id)
            ?.toRecipeInformation()
}