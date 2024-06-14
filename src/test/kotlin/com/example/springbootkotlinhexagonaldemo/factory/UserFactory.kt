package com.example.springbootkotlinhexagonaldemo.factory

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
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import java.time.Instant

object UserFactory {
    private var id = 0

    fun generalUser(): User {
        id++
        return User(
            id = UserId(id),
            personalInfo = UserPersonalInfo(
                name = UserName("user_$id"),
                email = Email("user_$id@dev.com")
            ),
            userStatus = UserStatus.ACTIVE,
            audit = Audit(
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            ),
            mileage = Mileage(
                id = MileageId(id),
                point = 0.toPositiveOrZeroInt()
            )
        )
    }

    fun User.userStatus(userStatus: UserStatus): User {
        this.userStatus = userStatus
        return this
    }

    fun generalUserJPAEntity(): UserJPAEntity {
        id++
        return UserJPAEntity(
            id = null,
            email = "user_$id@dev.com",
            name = "user_$id",
            userStatus = UserStatus.ACTIVE,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            mileage = MileageJPAEntity(
                id = null,
                point = 0
            )
        )
    }
}