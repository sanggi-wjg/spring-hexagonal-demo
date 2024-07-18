package com.raynor.hexagonal.domain.type.common

@JvmInline
value class Email(
    val value: String
) {
    init {
        require(value.isNotEmpty())
        // validate email. using regex, ... whatever
    }
}
