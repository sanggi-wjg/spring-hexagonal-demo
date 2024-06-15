package com.example.springbootkotlinhexagonaldemo.domain.type.embed

import java.time.Instant

data class Audit(
    val createdAt: Instant,
    val updatedAt: Instant,
)
