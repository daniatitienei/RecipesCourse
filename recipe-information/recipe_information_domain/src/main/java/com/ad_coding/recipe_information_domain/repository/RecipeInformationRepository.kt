package com.ad_coding.recipe_information_domain.repository

import com.ad_coding.recipe_information_domain.model.RecipeInformation

interface RecipeInformationRepository {
    suspend fun getInfo(id: String): RecipeInformation?
}