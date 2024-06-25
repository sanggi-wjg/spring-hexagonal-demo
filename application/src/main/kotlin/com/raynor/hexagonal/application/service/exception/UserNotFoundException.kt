package com.raynor.hexagonal.application.service.exception

import com.raynor.hexagonal.domain.type.id.UserId

class UserNotFoundException(override val message: String) : RuntimeException() {
    constructor(userId: UserId) : this("User not found: $userId")
}