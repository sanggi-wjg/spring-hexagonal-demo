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
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import java.time.Instant

object UserFactory {
    private var id = 0

    fun create(
        userStatus: UserStatus = UserStatus.ACTIVE
    ): User {
        id++
        return User(
            id = UserId(id),
            personalInfo = UserPersonalInfo(
                name = UserName("user_$id"),
                email = Email("user_$id@dev.com")
            ),
            userStatus = userStatus,
            audit = Audit(
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            ),
            mileage = Mileage(
                id = MileageId(id),
                point = 0.toPositiveOrZeroInt()
            ),
            mileageHistories = setOf(),
        )
    }
}