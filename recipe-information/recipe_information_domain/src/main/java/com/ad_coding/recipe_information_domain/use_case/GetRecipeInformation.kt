package com.ad_coding.recipe_information_domain.use_case

import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository

class GetRecipeInformation(
    private val repository: RecipeInformationRepository
) {
    suspend fun execute(id: String) =
        repository.getInfo(id = id)
}