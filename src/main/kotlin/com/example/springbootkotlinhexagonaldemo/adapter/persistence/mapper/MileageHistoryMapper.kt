package com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper

import com.example.springbootkotlinhexagonaldemo.domain.entity.MileageHistory
import com.example.springbootkotlinhexagonaldemo.domain.type.common.toPositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageHistoryId
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageHistoryJPAEntity

object MileageHistoryMapper {

    fun toDomain(mileageHistory: MileageHistoryJPAEntity): MileageHistory {
        return MileageHistory(
            id = MileageHistoryId(mileageHistory.id!!),
            beforeMileagePoint = mileageHistory.beforeMileagePoint.toPositiveOrZeroInt(),
            afterMileagePoint = mileageHistory.afterMileagePoint.toPositiveOrZeroInt(),
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
            beforeMileagePoint = mileageHistory.beforeMileagePoint.value,
            afterMileagePoint = mileageHistory.afterMileagePoint.value,
            point = mileageHistory.point,
            message = mileageHistory.message,
            createdAt = mileageHistory.audit.createdAt,
            updatedAt = mileageHistory.audit.updatedAt,
            mileage = MileageMapper.toJPAEntity(mileageHistory.mileage),
        )
    }
}