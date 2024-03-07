package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUserAdapterTest(
    private val userRepository: UserRepository,
    private val readUserAdapter: ReadUserAdapter,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
    }

    test("유저 전체 조회할 수 있어야 한다.") {
        // given
        val users = userRepository.saveAll(
            listOf(
                UserEntity("user_1@dev.com", "user_1"),
                UserEntity("user_2@dev.com", "user_2"),
                UserEntity("user_3@dev.com", "user_3"),
            )
        ).map { User.of(it) }

        // when
        readUserAdapter.findAll().also {
            // then
            it.size shouldBe 3
            it.forEach { findUser ->
                findUser.shouldBeEqualToComparingFields(users.first { findUser.email == it.email })
            }
        }
    }

    test("유저 ID로 조회할 수 있어야 한디.") {
        // given
        val users = userRepository.saveAll(
            listOf(
                UserEntity("user_1@dev.com", "user_1"),
                UserEntity("user_2@dev.com", "user_2"),
                UserEntity("user_3@dev.com", "user_3"),
            )
        ).map { User.of(it) }
        val user = users.first()

        // when
        readUserAdapter.findById(user.id).also {
            // then
            it!!.shouldBeEqualToComparingFields(user)
        }
        // then
        readUserAdapter.findById(0) shouldBe null
    }

    test("유저 이메일 존재 여부를 조회할 수 있어야 한다.") {
        // given
        val user = userRepository.save(UserEntity("user_1@dev.com", "user_1"))

        // when
        readUserAdapter.existsByEmail(user.email!!) shouldBe true
        readUserAdapter.existsByEmail("who_am_i@dev.com") shouldBe false
    }
})
