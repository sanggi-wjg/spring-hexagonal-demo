package com.raynor.hexagonal.domain.type.personal

@JvmInline
value class UserName(
    val value: String
) {
    init {
        // validate
    }
}
