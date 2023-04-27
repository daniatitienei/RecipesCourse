package com.ad_coding.recipe_information_data.mapper

import com.ad_coding.core.data.network.dto.ExtendedIngredientDto
import com.ad_coding.recipe_information_domain.model.ExtendedIngredient

fun ExtendedIngredientDto.toExtendedIngredient(): ExtendedIngredient =
    ExtendedIngredient(
        amount = amount,
        id = id,
        image = image.orEmpty(),
        originalName = originalName,
        unit = unit
    )