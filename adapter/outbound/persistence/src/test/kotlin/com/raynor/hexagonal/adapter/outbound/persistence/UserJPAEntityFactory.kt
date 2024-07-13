package com.raynor.hexagonal.adapter.outbound.persistence

import com.raynor.hexagonal.adapter.outbound.persistence.entity.MileageJPAEntity
import com.raynor.hexagonal.adapter.outbound.persistence.entity.UserJPAEntity
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
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
        val now = Instant.now()

        return userRepository.save(
            UserJPAEntity(
                id = null,
                email = "$uuid@dev.com",
                name = "user_$uuid",
                userStatus = userStatus,
                createdAt = now,
                updatedAt = Instant.now(),
                mileage = mileageRepository.save(
                    MileageJPAEntity(
                        id = null,
                        point = mileagePoint
                    )
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
