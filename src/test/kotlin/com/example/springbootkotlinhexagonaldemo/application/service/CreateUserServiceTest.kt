package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.service.user.CreateUserService
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateUserServiceTest(
    private val createUserService: CreateUserService,
) : BehaviorSpec({

    given("유저 생성 정보로") {
        val command = CreateUserUseCase.Command(
            email = Email("JhZpR-1@example.com"),
            name = UserName("홍길동-1"),
        )

        `when`("유저 생성시") {
            val createdUser = createUserService.createUser(command)

            then("유저 생성 정보를 토대로 유저가 생성 되어야 한다.") {
                val expectedUser = User(
                    email = command.email,
                    name = command.name,
                )
                val expectedMileage = Mileage()

                createdUser.shouldBeEqualToIgnoringFields(
                    expectedUser,
                    User::id,
                    User::audit,
                    User::mileage,
                    User::mileageHistories,
                )
                createdUser.mileage.shouldBeEqualToIgnoringFields(
                    expectedMileage,
                    Mileage::id,
                )
            }

            then("존재하는 이메일이라면 에러가 발생해야 한다.") {
                val command2 = CreateUserUseCase.Command(
                    email = command.email,
                    name = UserName("고길동"),
                )

                shouldThrow<IllegalArgumentException> {
                    createUserService.createUser(command2)
                }
            }
        }
    }
})
