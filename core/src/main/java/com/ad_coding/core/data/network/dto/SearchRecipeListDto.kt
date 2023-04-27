package com.ad_coding.core.data.network.dto

data class SearchRecipeListDto(
    val number: Int = 0,
    val offset: Int = 0,
    val results: List<ResultDto> = listOf(),
    val totalResults: Int = 0
)