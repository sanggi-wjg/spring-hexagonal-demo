package com.example.springbootkotlinhexagonaldemo.factory

import com.raynor.hexagonal.domain.entity.Mileage
import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.id.MileageId
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName
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