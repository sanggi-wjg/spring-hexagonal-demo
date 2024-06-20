package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName

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