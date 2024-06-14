package com.example.springbootkotlinhexagonaldemo.domain.entity

import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.common.PositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.RootEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import java.time.Instant

@RootEntity
class User(
    val id: UserId?,
    var personalInfo: UserPersonalInfo,
    var userStatus: UserStatus,
    var audit: Audit,
    var mileage: Mileage,
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
        mileage = Mileage(id = null, point = PositiveOrZeroInt(0)),
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

    fun update(modification: UserModification) {
        modification.userStatus?.let { this.userStatus = it }

        if (modification.email != null && modification.name != null) {
            this.personalInfo = UserPersonalInfo(email = modification.email, name = modification.name)
        } else {
            modification.email?.let {
                this.personalInfo = UserPersonalInfo(email = modification.email, name = this.personalInfo.name)
            }
            modification.name?.let {
                this.personalInfo = UserPersonalInfo(email = this.personalInfo.email, name = it)
            }
        }
    }
}
