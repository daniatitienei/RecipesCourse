package com.ad_coding.core.data.network.dto

data class WinePairingDto(
    val pairedWines: List<String> = listOf(),
    val pairingText: String = "",
    val productMatches: List<ProductMatcheDto> = listOf()
)