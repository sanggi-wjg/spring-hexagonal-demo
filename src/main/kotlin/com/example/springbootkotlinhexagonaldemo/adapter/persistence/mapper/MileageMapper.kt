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

object MileageMapper {

    fun toDomain(mileage: MileageJPAEntity): Mileage {
        return Mileage(
            id = MileageId(mileage.id!!),
            point = mileage.point.toPositiveOrZeroInt(),
        )
    }

    fun toJPAEntity(mileage: Mileage): MileageJPAEntity {
        return MileageJPAEntity(
            id = mileage.id?.value,
            point = mileage.point.value,
        )
    }
}