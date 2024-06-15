package com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
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
                MileageMapper.toDomain(it)
            },
            mileageHistories = user.mileage.mileageHistories.map {
                MileageHistoryMapper.toDomain(it)
            }.toSet()
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