package com.ad_coding.core.data.network.dto

data class StepDto(
    val equipment: List<EquipmentDto> = listOf(),
    val ingredients: List<IngredientDto> = listOf(),
    val length: LengthDto = LengthDto(),
    val number: Int = 0,
    val step: String = ""
)