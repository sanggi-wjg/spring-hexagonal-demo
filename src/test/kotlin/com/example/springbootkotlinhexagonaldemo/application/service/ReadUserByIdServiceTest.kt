package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.service.user.ReadUserByIdService
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUserByIdServiceTest(
    private val readUserByIdService: ReadUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    afterEach {
        unmockkAll()
        clearAllMocks()
    }

    test("존재하지 않는 유저 ID 조회시 에러가 발생하여야 한다.") {
        // given
        val query = ReadUserByIdUseCase.Query(
            userId = UserId(1)
        )

        // mock
        every {
            readUserPort.findById(query.userId)
        } returns null

        // when
        shouldThrow<IllegalArgumentException> {
            readUserByIdService.readById(query)
        }
    }

    test("유저 ID 조회할 수 있어야 한다.") {
        // given
        val userFixture = UserFactory.create()
        val query = ReadUserByIdUseCase.Query(
            userId = userFixture.id!!
        )

        // mock
        every {
            readUserPort.findById(userFixture.id!!)
        } returns userFixture

        // when
        val user = readUserByIdService.readById(query)

        // then
        user.shouldBeEqualToComparingFields(userFixture)
    }
})