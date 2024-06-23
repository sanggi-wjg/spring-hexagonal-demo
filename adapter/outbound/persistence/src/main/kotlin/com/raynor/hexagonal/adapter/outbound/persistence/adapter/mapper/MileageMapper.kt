package com.raynor.hexagonal.adapter.outbound.persistence.adapter.mapper

import com.raynor.hexagonal.adapter.outbound.persistence.entity.MileageJPAEntity
import com.raynor.hexagonal.domain.entity.Mileage
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.id.MileageId

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