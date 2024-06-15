package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.service.user.CreateUserService
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateUserServiceTest(
    private val createUserService: CreateUserService,
) : FunSpec({

    test("유저를 생성할 수 있어야 한다.") {
        // given
        val command = CreateUserUseCase.Command(
            email = Email("JhZpR-1@example.com"),
            name = UserName("홍길동-1"),
        )

        val expectedUser = User(
            email = command.email,
            name = command.name,
        )
        val expectedMileage = Mileage()

        // when
        val createdUser = createUserService.createUser(command)

        // then
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

    test("존재하는 이메일이라면 에러가 발생해야 한다.") {
        // given
        val command1 = CreateUserUseCase.Command(
            email = Email("customer_2@dev.com"),
            name = UserName("홍길동"),
        )
        val command2 = CreateUserUseCase.Command(
            email = command1.email,
            name = UserName("고길동"),
        )

        createUserService.createUser(command1)

        // when ~ then
        shouldThrow<IllegalArgumentException> {
            createUserService.createUser(command2)
        }
    }
})