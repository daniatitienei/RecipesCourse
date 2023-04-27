package com.ad_coding.core.data.network.dto

data class AnalyzedInstructionDto(
    val name: String = "",
    val steps: List<StepDto> = listOf()
)