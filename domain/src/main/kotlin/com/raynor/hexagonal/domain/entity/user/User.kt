package com.raynor.hexagonal.domain.entity.user

import com.raynor.hexagonal.domain.annotations.RootEntity
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.common.plus
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName
import java.time.Instant

@RootEntity
class User(
    val id: UserId?,
    val personalInfo: UserPersonalInfo,
    val userStatus: UserStatus,
    val audit: Audit,
    // 적립금 부분은 컨텍스트 영역을 따로 가져가는게 좋아보이지만 데모 구현을 위해서 포함하여 구현
    val mileage: Mileage,
    val mileageHistories: Set<MileageHistory>
) {
    constructor(
        email: Email,
        name: UserName,
    ) : this(
        id = null,
        personalInfo = UserPersonalInfo(
            email = email,
            name = name,
        ),
        userStatus = UserStatus.ACTIVE,
        audit = Audit(
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        ),
        mileage = Mileage(),
        mileageHistories = mutableSetOf(),
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (this.id == null || other.id == null) return false
        return id == (other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun copy(
        personalInfo: UserPersonalInfo = this.personalInfo,
        userStatus: UserStatus = this.userStatus,
        audit: Audit = this.audit,
        mileage: Mileage = this.mileage,
        mileageHistories: Set<MileageHistory> = this.mileageHistories
    ) = User(
        id = this.id,
        personalInfo = personalInfo,
        userStatus = userStatus,
        audit = audit,
        mileage = mileage,
        mileageHistories = mileageHistories,
    )

    fun isActive(): Boolean {
        return this.userStatus == UserStatus.ACTIVE
    }

    fun isLeft(): Boolean {
        return this.userStatus == UserStatus.LEFT
    }

    fun update(
        inputEmail: Email?,
        inputName: UserName?,
        inputUserStatus: UserStatus?
    ): User {
        check(!this.isLeft()) {
            "left user cannot be updated"
        }

        return this.copy(
            personalInfo = this.personalInfo.copy(
                email = inputEmail ?: this.personalInfo.email,
                name = inputName ?: this.personalInfo.name
            ),
            userStatus = inputUserStatus ?: this.userStatus,
            audit = this.audit.copy(
                updatedAt = Instant.now(),
            )
        )
    }

    fun putMileagePoint(
        inputPoint: Int,
        message: String? = null
    ): User {
        val mightBePoint = this.mileage.point.plus(inputPoint)
        check(mightBePoint.value >= 0) {
            "user has not enough mileage point"
        }

        val newHistory = MileageHistory(
            currentPoint = this.mileage.point,
            inputPoint = inputPoint,
            message = message,
            mileage = this.mileage,
        )

        return this.copy(
            mileage = this.mileage.copy(mightBePoint),
            mileageHistories = this.mileageHistories + setOf(newHistory)
        )
    }
}
