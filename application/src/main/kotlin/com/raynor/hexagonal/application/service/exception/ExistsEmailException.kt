package com.raynor.hexagonal.application.service.exception


class ExistsEmailException(override val message: String) : IllegalArgumentException("Email already exists: $message")