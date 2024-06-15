package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.MileageHistoryMapper
import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.MileageMapper
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileageHistoryPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.MileageHistory
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageHistoryJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageHistoryRepository

@Adapter
class WriteMileageHistoryAdapter(
    private val mileageHistoryRepository: MileageHistoryRepository,
) : WriteMileageHistoryPort {

    override fun create(mileageHistory: MileageHistory): MileageHistory {
        return save(mileageHistory).let { MileageHistoryMapper.toDomain(it) }
    }

    private fun save(mileageHistory: MileageHistory): MileageHistoryJPAEntity {
        val mileageHistoryJPAEntity = MileageHistoryMapper.toJPAEntity(mileageHistory)
        return mileageHistoryRepository.save(mileageHistoryJPAEntity)
    }
}