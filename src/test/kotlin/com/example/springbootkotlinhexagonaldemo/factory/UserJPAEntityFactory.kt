package com.example.springbootkotlinhexagonaldemo.factory

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.raynor.hexagonal.domain.enum.UserStatus
import java.time.Instant
import java.util.*

class UserJPAEntityFactory(
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val mileageHistoryRepository: MileageHistoryRepository,
) {

    fun create(
        userStatus: UserStatus = UserStatus.ACTIVE,
        mileagePoint: Int = 0,
    ): UserJPAEntity {
        val uuid = UUID.randomUUID()

        return userRepository.save(
            UserJPAEntity(
                id = null,
                email = "$uuid@dev.com",
                name = "user_$uuid",
                userStatus = userStatus,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                mileage = mileageRepository.save(
                    MileageJPAEntity(id = null, point = mileagePoint)
                )
            )
        )
    }

    fun deleteAll() {
        listOf(
            userRepository,
            mileageHistoryRepository,
            mileageRepository,
        ).forEach {
            it.deleteAllInBatch()
        }
    }
}