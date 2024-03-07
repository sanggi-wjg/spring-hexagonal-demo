package com.example.springbootkotlinhexagonaldemo.infrastructure.repository

import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserRepositoryTest(
    private val userRepository: UserRepository,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
    }

    test("존재하는 유저에 대해 이메일로 조회할 수 있어야 한다.") {
        // given
        val user = userRepository.save(UserEntity("snow@dev.com", "snow"))

        // when
        val isExists = userRepository.existsByEmail(user.email!!)

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
