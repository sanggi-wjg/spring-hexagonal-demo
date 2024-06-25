package com.raynor.hexagonal.domain.type.embed

import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.personal.UserName

data class UserPersonalInfo(
    val email: Email,
    val name: UserName
)
