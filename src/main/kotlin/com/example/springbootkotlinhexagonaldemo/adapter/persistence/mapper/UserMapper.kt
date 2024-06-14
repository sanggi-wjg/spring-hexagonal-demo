package com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper

import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.common.toPositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageId
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity

object UserMapper {

    fun toDomain(user: UserJPAEntity): User {
        return User(
            id = UserId(user.id!!),
            personalInfo = UserPersonalInfo(
                email = Email(user.email),
                name = UserName(user.name),
            ),
            userStatus = user.userStatus,
            audit = Audit(
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
            ),
            mileage = user.mileage.let {
                Mileage(
                    id = MileageId(it.id!!),
                    point = it.point.toPositiveOrZeroInt(),
                )
            }
        )
    }

    fun toJPAEntity(user: User): UserJPAEntity {
        return UserJPAEntity(
            id = user.id?.value,
            email = user.personalInfo.email.value,
            name = user.personalInfo.name.value,
            userStatus = user.userStatus,
            createdAt = user.audit.createdAt,
            updatedAt = user.audit.updatedAt,
            mileage = MileageJPAEntity(
                id = user.mileage.id?.value,
                point = user.mileage.point.value
            )
        )
    }
}