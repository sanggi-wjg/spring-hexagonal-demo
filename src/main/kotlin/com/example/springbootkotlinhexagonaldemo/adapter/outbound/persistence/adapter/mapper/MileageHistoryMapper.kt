package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageHistoryJPAEntity
import com.raynor.hexagonal.domain.entity.MileageHistory
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.id.MileageHistoryId

object MileageHistoryMapper {

    fun toDomain(mileageHistory: MileageHistoryJPAEntity): MileageHistory {
        return MileageHistory(
            id = MileageHistoryId(mileageHistory.id!!),
            beforePoint = mileageHistory.beforePoint.toPositiveOrZeroInt(),
            afterPoint = mileageHistory.afterPoint.toPositiveOrZeroInt(),
            point = mileageHistory.point,
            message = mileageHistory.message,
            audit = Audit(
                createdAt = mileageHistory.createdAt,
                updatedAt = mileageHistory.updatedAt,
            ),
            mileage = MileageMapper.toDomain(mileageHistory.mileage),
        )
    }

    fun toJPAEntity(mileageHistory: MileageHistory): MileageHistoryJPAEntity {
        return MileageHistoryJPAEntity(
            id = mileageHistory.id?.value,
            beforePoint = mileageHistory.beforePoint.value,
            afterPoint = mileageHistory.afterPoint.value,
            point = mileageHistory.point,
            message = mileageHistory.message,
            createdAt = mileageHistory.audit.createdAt,
            updatedAt = mileageHistory.audit.updatedAt,
            mileage = MileageMapper.toJPAEntity(mileageHistory.mileage),
        )
    }
}