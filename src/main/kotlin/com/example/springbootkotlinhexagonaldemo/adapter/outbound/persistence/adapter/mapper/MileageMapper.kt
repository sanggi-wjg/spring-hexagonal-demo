package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.type.common.toPositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageId

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