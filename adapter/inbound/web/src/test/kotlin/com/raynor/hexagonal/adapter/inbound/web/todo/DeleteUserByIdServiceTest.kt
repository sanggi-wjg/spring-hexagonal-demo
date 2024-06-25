package com.raynor.hexagonal.adapter.inbound.web.todo

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.inbound.usecase.DeleteUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.user.DeleteUserByIdService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DeleteUserByIdServiceTest(
    private val deleteUserByIdService: DeleteUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저 정보를 삭제할 수 있어야 한다.") {
        // given
        val userFixture = UserFactory.create()
        val command = DeleteUserByIdUseCase.Command(userFixture.id!!)

        // mock
        every {
            readUserPort.findById(userFixture.id!!)
        } returns userFixture

        every {
            writeUserPort.delete(any())
        } returns Unit

        // when
        val result = deleteUserByIdService.deleteUserById(command)

        // then
        result shouldBe true
    }
})