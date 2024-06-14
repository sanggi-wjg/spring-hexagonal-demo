package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.service.user.DeleteUserByIdService
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DeleteUserByIdServiceTest(
    private val deleteUserByIdService: DeleteUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저 정보를 수정할 수 있어야 한다.") {
        // given
        val userMock = UserFactory.generalUser()

        // mock
        every {
            readUserPort.findById(userMock.id!!)
        } returns userMock

        every {
            writeUserPort.delete(any())
        } returns Unit

        // when
        val user = deleteUserByIdService.deleteUserById(userMock.id!!)

        // then
        user.shouldBeEqualToComparingFields(userMock)
    }
})