package com.raynor.hexagonal.adapter.inbound.web.todo

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.inbound.usecase.ReadUsersUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.service.user.ReadUsersService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUsersServiceTest(
    private val readUsersService: ReadUsersService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    afterEach {
        unmockkAll()
        clearAllMocks()
    }

    test("전체 유저 조회를 할 수 있어야 한다.") {
        // given
        val query = ReadUsersUseCase.Query(
            userIds = null
        )
        val userFixtures = listOf(
            UserFactory.create(),
            UserFactory.create(),
        )

        // mock
        every {
            readUserPort.findAll(
                userIds = query.userIds
            )
        } returns userFixtures

        // when
        val users = readUsersService.readUsers(query)

        // then
        users.forEach { user ->
            user.shouldBeEqualToComparingFields(userFixtures.first { user.id == it.id })
        }
    }
})