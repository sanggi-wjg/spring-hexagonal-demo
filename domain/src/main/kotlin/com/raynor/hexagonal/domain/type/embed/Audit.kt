package com.raynor.hexagonal.domain.type.embed

import java.time.Instant

data class Audit(
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    companion object {
        fun now() = Instant.now().let {
            Audit(
                createdAt = it,
                updatedAt = it
            )
        }
    }
}
