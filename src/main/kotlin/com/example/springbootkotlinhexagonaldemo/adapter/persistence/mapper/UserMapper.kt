package com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity

object UserMapper {

    fun toDomain(user: UserJPAEntity): User {
        return User(
            id = UserId(user.id!!),
            personalInfo = UserPersonalInfo(
                email = Email(user.email),
                name = UserName(user.name),
            ),
            userStatus = user.userStatus
        )
    }

    fun toJPAEntity(user: User): UserJPAEntity {
        return UserJPAEntity(
            id = user.id?.value,
            email = user.personalInfo.email.value,
            name = user.personalInfo.name.value,
            userStatus = user.userStatus
        )
    }
}