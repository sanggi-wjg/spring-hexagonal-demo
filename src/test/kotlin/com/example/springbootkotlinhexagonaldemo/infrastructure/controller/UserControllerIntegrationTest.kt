package com.example.springbootkotlinhexagonaldemo.infrastructure.controller

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.request.UserCreationDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.request.UserModificationDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.response.scheme.MileageBasicScheme
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.factory.UserJPAEntityFactory
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
@EnableWebMvc
@AutoConfigureMockMvc
@Transactional
class UserControllerIntegrationTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val mileageHistoryRepository: MileageHistoryRepository,
) : FunSpec({

    val factory = UserJPAEntityFactory(
        userRepository = userRepository,
        mileageRepository = mileageRepository,
        mileageHistoryRepository = mileageHistoryRepository
    )

    beforeEach {
        factory.deleteAll()
    }

    test("[POST] /api/v1/users") {
        // given
        val request = UserCreationDto(
            email = "${UUID.randomUUID()}@dev.com",
            name = "test",
        )

        // when ~ then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(
                MockMvcResultMatchers.header().let { header ->
                    val userId = userRepository.findAll().last().id
                    header.string("Location", "/users/$userId")
                }
            )
    }

    test("[GET] /api/v1/users") {
        // given
        val user1 = factory.create(mileagePoint = 123)
        val user2 = factory.create(userStatus = UserStatus.LEFT, mileagePoint = 456)

        val expected = listOf(
            UserResponseDto(
                id = user1.id!!,
                email = user1.email,
                name = user1.name,
                userStatus = user1.userStatus,
                createdAt = user1.createdAt,
                updatedAt = user1.updatedAt,
            ),
            UserResponseDto(
                id = user2.id!!,
                email = user2.email,
                name = user2.name,
                userStatus = user2.userStatus,
                createdAt = user2.createdAt,
                updatedAt = user2.updatedAt,
            ),
        )
        val response = objectMapper.writeValueAsString(expected)

        // when ~ then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(response))
    }

    test("[GET] /api/v1/users/{userId}") {
        // given
        val user = factory.create()

        val expected = UserDetailResponseDto(
            id = user.id!!,
            email = user.email,
            name = user.name,
            userStatus = user.userStatus,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt,
            mileage = MileageBasicScheme(
                id = user.mileage.id!!,
                point = user.mileage.point,
            ),
            mileageHistories = emptyList(),
        )
        val response = objectMapper.writeValueAsString(expected)

        // when ~ then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/${user.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(response))
    }

    test("[PATCH] /api/v1/users/{userId}") {
        // given
        val user = factory.create()

        val request = UserModificationDto(
            email = "changed-${user.email}",
            name = "changed-${user.name}",
            userStatus = UserStatus.LEFT,
        )

        // when ~ then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/v1/users/${user.id}")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(
                MockMvcResultMatchers.content().let {
                    val findUser = userRepository.findByIdOrNull(user.id!!)!!
                    val expected = UserResponseDto(
                        id = user.id!!,
                        email = request.email!!,
                        name = request.name!!,
                        userStatus = request.userStatus!!,
                        createdAt = user.createdAt,
                        updatedAt = findUser.updatedAt,
                    )
                    val response = objectMapper.writeValueAsString(expected)
                    it.string(response)
                }
            )
    }

    test("[DELETE] /api/v1/users/{userId}") {
        // given
        val user = factory.create()

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/${user.id}"))
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())

        // then
        userRepository.findByIdOrNull(user.id!!) shouldBe null
    }
})