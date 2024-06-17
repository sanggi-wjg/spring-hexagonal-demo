package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper.MileageHistoryMapper
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageHistoryJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileageHistoryPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.MileageHistory
import org.springframework.stereotype.Component

@Component
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