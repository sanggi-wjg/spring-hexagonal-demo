package com.example.springbootkotlinhexagonaldemo.domain

import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val userStatus: UserStatus,
) {
    companion object {
        fun of(userEntity: UserEntity) = User(
            id = userEntity.id!!,
            name = userEntity.name!!,
            email = userEntity.email!!,
            userStatus = userEntity.userStatus
        )
    }
}
