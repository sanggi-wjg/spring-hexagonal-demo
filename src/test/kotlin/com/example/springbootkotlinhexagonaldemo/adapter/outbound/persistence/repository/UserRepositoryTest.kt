package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
import com.raynor.hexagonal.domain.enum.UserStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
class UserRepositoryTest(
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
    }

    test("존재하는 유저에 대해 이메일로 조회할 수 있어야 한다.") {
        // given
        val user = userRepository.save(
            UserJPAEntity(
                id = null,
                email = "snow@dev.com",
                name = "snow",
                userStatus = UserStatus.ACTIVE,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                mileage = MileageJPAEntity(
                    id = null,
                    point = 0
                )
            ).also {
                mileageRepository.save(it.mileage)
            }
        )

        // when
        val isExists = userRepository.existsByEmail(user.email)

        // then
        isExists shouldBe true
    }

    test("존재하지 않는 유저에 대해 이메일로 조회할 수 있어야 한다.") {
        // given
        val email = "snow@dev.com"

        // when
        val isExists = userRepository.existsByEmail(email)

        // then
        isExists shouldBe false
    }
})
