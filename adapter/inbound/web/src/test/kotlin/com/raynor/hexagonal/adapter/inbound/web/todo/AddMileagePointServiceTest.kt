package com.raynor.hexagonal.adapter.inbound.web.todo

import com.raynor.hexagonal.application.port.inbound.usecase.AddMileagePointUseCase
import com.raynor.hexagonal.application.port.inbound.usecase.CreateUserUseCase
import com.raynor.hexagonal.application.port.inbound.usecase.ReadUserByIdUseCase
import com.raynor.hexagonal.application.service.user.AddMileagePointService
import com.raynor.hexagonal.application.service.user.CreateUserService
import com.raynor.hexagonal.application.service.user.ReadUserByIdService
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.personal.UserName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class AddMileagePointServiceTest(
    private val addMileagePointService: AddMileagePointService,
    private val createUserService: CreateUserService,
    private val readUserByIdService: ReadUserByIdService,
) : BehaviorSpec({

    given("활성 상태 유저가 있을때") {
        val user = createUserService.createUser(
            CreateUserUseCase.Command(
                email = Email("${UUID.randomUUID()}@dev.com"),
                name = UserName("둘리"),
            )
        )
        val userId = user.id!!

        `when`("적립금을 가감 받았다면") {
            val addPoint = 1234
            val minusPoint = -123

            addMileagePointService.addMileagePoint(
                AddMileagePointUseCase.Command(
                    userId = userId,
                    mileagePoint = addPoint,
                    message = null,
                )
            )
            val updatedUser = addMileagePointService.addMileagePoint(
                AddMileagePointUseCase.Command(
                    userId = userId,
                    mileagePoint = minusPoint,
                    message = "잘못 발급하여 눈물을 머금고 차감 합니다."
                )
            )

            then("적립금 변경과 적립금 변경 내역이 생성 되어야 한다.") {
                val findUser = readUserByIdService.readById(
                    ReadUserByIdUseCase.Query(
                        userId = userId
                    )
                )
                findUser shouldBe updatedUser
                findUser.mileage.point shouldBe 1111.toPositiveOrZeroInt()
                findUser.mileageHistories.size shouldBe 2
            }
        }
    }
})