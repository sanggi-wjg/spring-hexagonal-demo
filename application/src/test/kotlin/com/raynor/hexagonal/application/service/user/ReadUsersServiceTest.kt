package com.raynor.hexagonal.application.service.user

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.UserFactory
import com.raynor.hexagonal.application.port.inbound.usecase.ReadUsersUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        ReadUsersService::class,
    ]
)
class ReadUsersServiceTest(
    private val readUsersService: ReadUsersService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("[ReadUsersServiceTest] 유저들을 조회할 수 있어야 한다.") {
        // given
        val userFixtures = listOf(
            UserFactory.create(),
            UserFactory.create(),
        )

        // mock
        every {
            readUserPort.findAll()
        } returns userFixtures

        // when
        val query = ReadUsersUseCase.Query(userIds = null)
        val foundUsers = readUsersService.readUsers(query)

        // then
        foundUsers.forEach { user ->
            val expect = userFixtures.first { user.id == it.id }
            user.shouldBeEqualToComparingFields(expect)
        }
    }
})
