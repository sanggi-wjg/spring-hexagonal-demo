package com.example.springbootkotlinhexagonaldemo.domain.type.embed

import java.time.Instant

data class Audit(
    val createdAt: Instant,
    var updatedAt: Instant,
) {
    fun updated() {
        this.updatedAt = Instant.now()
    }
}
