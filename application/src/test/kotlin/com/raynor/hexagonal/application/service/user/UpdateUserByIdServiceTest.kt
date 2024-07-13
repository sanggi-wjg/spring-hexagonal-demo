package com.raynor.hexagonal.application.service.user

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import io.kotest.core.spec.style.FunSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateUserByIdServiceTest(
    private val updateUserByIdService: UpdateUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

//    test("유저 정보를 수정할 수 있어야 한다.") {
//        // given
//        val userFixture = UserFactory.create()
//        val command = UpdateUserByIdUseCase.Command(
//            userId = userFixture.id!!,
//            email = Email("email@dev.com"),
//            name = UserName("name"),
//            userStatus = UserStatus.LEFT,
//        )
//
//        val expected = User(
//            id = userFixture.id,
//            personalInfo = UserPersonalInfo(
//                email = command.email!!,
//                name = command.name!!,
//            ),
//            userStatus = command.userStatus!!,
//            audit = Audit(
//                createdAt = userFixture.audit.createdAt,
//                updatedAt = Instant.now(),
//            ),
//            mileage = userFixture.mileage,
//            mileageHistories = userFixture.mileageHistories,
//        )
//
//        // mock
//        every {
//            readUserPort.findById(userFixture.id!!)
//        } returns userFixture
//
//        every {
//            writeUserPort.update(any())
//        } returns expected
//
//        // when
//        val user = updateUserByIdService.updateUserById(command)
//
//        // then
//        user.shouldBeEqualToComparingFields(expected)
//    }
})