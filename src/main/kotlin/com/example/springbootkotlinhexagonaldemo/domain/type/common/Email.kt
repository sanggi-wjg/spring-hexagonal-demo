package com.example.springbootkotlinhexagonaldemo.domain.type.common

@JvmInline
value class Email(
    val value: String
) {
    init {
        // validate email. using regex, ... whatever
    }
}
