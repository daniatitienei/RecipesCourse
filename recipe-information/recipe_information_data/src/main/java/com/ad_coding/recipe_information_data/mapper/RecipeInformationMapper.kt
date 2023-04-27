package com.ad_coding.recipe_information_data.mapper

import com.ad_coding.core.data.network.dto.RecipeInformationDto
import com.ad_coding.recipe_information_domain.model.RecipeInformation

fun RecipeInformationDto.toRecipeInformation(): RecipeInformation =
    RecipeInformation(
        id = id,
        image = image,
        title = title,
        summary = summary,
        extendedIngredients = extendedIngredients.map { it.toExtendedIngredient() },
        instructions = instructions,
    )