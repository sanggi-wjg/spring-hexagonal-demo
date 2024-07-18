package com.raynor.hexagonal.application

import com.raynor.hexagonal.domain.entity.user.Mileage
import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.id.MileageId
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName

object UserFactory {
    // UserFactory 가 두개 이상의 모듈에서 필요하니 공통 모듈 한개 생성하여 test impl 경우만 추가해도 좋댜.
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
            audit = Audit.now(),
            mileage = Mileage(
                id = MileageId(id),
                point = 0.toPositiveOrZeroInt()
            ),
            mileageHistories = setOf(),
        )
    }
}