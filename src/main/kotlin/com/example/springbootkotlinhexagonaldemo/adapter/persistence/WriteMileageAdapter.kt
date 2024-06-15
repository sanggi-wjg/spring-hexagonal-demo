package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.MileageMapper
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileagePort
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageRepository

@Adapter
class WriteMileageAdapter(
    private val mileageRepository: MileageRepository,
) : WriteMileagePort {

    override fun create(mileage: Mileage): Mileage {
        return save(mileage).let { MileageMapper.toDomain(it) }
    }

    override fun update(mileage: Mileage): Mileage {
        return save(mileage).let { MileageMapper.toDomain(it) }
    }

    private fun save(mileage: Mileage): MileageJPAEntity {
        val mileageJPAEntity = MileageMapper.toJPAEntity(mileage)
        return mileageRepository.save(mileageJPAEntity)
    }
}