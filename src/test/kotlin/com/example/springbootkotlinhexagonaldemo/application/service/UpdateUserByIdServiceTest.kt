package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateUserByIdServiceTest(
    private val updateUserByIdService: UpdateUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저 정보를 수정할 수 있어야 한다.") {
        // given
        val findUserMock = User(
            id = UserId(1),
            personalInfo = UserPersonalInfo(name = UserName("엥"), email = Email("dev@dev.com")),
            userStatus = UserStatus.ACTIVE,
        )
        val userModification = UserModification(
            name = UserName("홍길동"),
            email = Email("JhZpR@example.com"),
            userStatus = UserStatus.LEFT,
        )
        val userMock = User(
            id = findUserMock.id,
            personalInfo = UserPersonalInfo(name = userModification.name!!, email = userModification.email!!),
            userStatus = userModification.userStatus!!,
        )

        // mock
        every {
            readUserPort.findById(userMock.id!!)
        } returns findUserMock

        every {
            writeUserPort.update(any())
        } returns userMock

        // when
        val user = updateUserByIdService.updateUserById(userMock.id!!, userModification)

        // then
        user.shouldBeEqualToComparingFields(userMock)
    }
})