package com.ad_coding.recipe_list_data.mapper

import com.ad_coding.core.data.network.dto.RecipeDto
import com.ad_coding.core.data.network.dto.ResultDto
import com.ad_coding.recipe_list_domain.model.Recipe

fun RecipeDto.toRecipe(): Recipe =
    Recipe(
        id = id,
        image = image,
        summary = summary,
        title = title
    )

fun ResultDto.toRecipe(): Recipe =
    Recipe(
        id = id,
        image = image,
        title = title
    )