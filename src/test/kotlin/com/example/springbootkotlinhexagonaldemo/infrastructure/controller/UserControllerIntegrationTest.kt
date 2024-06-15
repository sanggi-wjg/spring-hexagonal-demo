package com.example.springbootkotlinhexagonaldemo.infrastructure.controller

import com.example.springbootkotlinhexagonaldemo.factory.UserJPAEntityFactory
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageHistoryRepository
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import io.kotest.core.spec.style.FunSpec
import jakarta.transaction.Transactional
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@ExtendWith(SpringExtension::class)
@SpringBootTest
@EnableWebMvc
@AutoConfigureMockMvc
@Transactional
class UserControllerIntegrationTest(
    private val mockMvc: MockMvc,
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val mileageHistoryRepository: MileageHistoryRepository,
) : FunSpec({

    val factory = UserJPAEntityFactory(
        userRepository = userRepository,
        mileageRepository = mileageRepository,
        mileageHistoryRepository = mileageHistoryRepository
    )

    test("[GET] /users") {
        // given
        val user1 = factory.create(mileagePoint = 123)
        val user2 = factory.create(userStatus = UserStatus.LEFT, mileagePoint = 456)

        val endPoint = "/users"
        mockMvc.perform(MockMvcRequestBuilders.get(endPoint))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
})