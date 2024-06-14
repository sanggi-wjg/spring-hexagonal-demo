package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory.userStatus
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUserAdapterTest(
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val readUserAdapter: ReadUserAdapter,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
    }

    test("유저 전체 조회할 수 있어야 한다.") {
        // given
        val userJPAEntities = userRepository.saveAll(
            listOf(
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
            )
        )
        val users = userJPAEntities.map { UserMapper.toDomain(it) }

        // when ~ then
        readUserAdapter.findAll().also { findUsers ->
            findUsers.size shouldBe 3
            findUsers.forEach { user ->
                val expectedUser = users.first { user.personalInfo.email == it.personalInfo.email }
                user.shouldBeEqualToComparingFields(expectedUser)
            }
        }
    }

    test("유저 ID로 조회할 수 있어야 한디.") {
        // given
        val userJPAEntities = userRepository.saveAll(
            listOf(
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
                UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
            )
        )
        val users = userJPAEntities.map { UserMapper.toDomain(it) }
        val user = users.last()

        // when ~ then
        readUserAdapter.findById(user.id!!)!!.shouldBeEqualToComparingFields(user)
    }

    test("유저 이메일 존재 여부를 조회할 수 있어야 한다.") {
        // given
        val userJPAEntity = userRepository.save(
            UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
        )
        val user = UserMapper.toDomain(userJPAEntity)

        // when
        readUserAdapter.existsByEmail(user.personalInfo.email) shouldBe true
        readUserAdapter.existsByEmail(Email("who_am_i@dev.com")) shouldBe false
    }
})
