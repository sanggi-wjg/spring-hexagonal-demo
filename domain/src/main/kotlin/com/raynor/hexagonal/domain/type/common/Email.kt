package com.raynor.hexagonal.domain.type.common

@JvmInline
value class Email(
    val value: String
) {
    init {
        // validate email. using regex, ... whatever
    }
}
