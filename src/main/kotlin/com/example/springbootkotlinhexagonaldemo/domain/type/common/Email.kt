package com.example.springbootkotlinhexagonaldemo.domain.type.common

data class Email(
    val value: String
) {
    init {
        // validate email. using regex, ... whatever
    }
}
