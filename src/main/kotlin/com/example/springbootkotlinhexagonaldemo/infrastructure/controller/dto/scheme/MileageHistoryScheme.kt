package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.scheme

import java.time.Instant

data class MileageHistoryScheme(
    val id: Int,
    val beforePoint: Int,
    val afterPoint: Int,
    val point: Int,
    val message: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)