package com.ad_coding.core.data.network.dto

data class MeasuresDtoX(
    val metric: MetricDtoX = MetricDtoX(),
    val us: UsDtoX = UsDtoX()
)