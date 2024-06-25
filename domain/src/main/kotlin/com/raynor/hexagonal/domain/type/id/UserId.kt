package com.raynor.hexagonal.domain.type.id

@JvmInline
value class UserId(
    val value: Int
) {
    init {
        require(value > 0) { "UserId must be greater than zero" }
    }
}
