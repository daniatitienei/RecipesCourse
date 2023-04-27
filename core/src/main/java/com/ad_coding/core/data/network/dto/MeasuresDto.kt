package com.ad_coding.core.data.network.dto

data class MeasuresDto(
    val metric: MetricDto = MetricDto(),
    val us: UsDto = UsDto()
)