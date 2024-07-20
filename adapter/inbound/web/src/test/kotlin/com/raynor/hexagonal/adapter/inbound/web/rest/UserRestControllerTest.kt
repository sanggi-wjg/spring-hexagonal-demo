package com.raynor.hexagonal.adapter.inbound.web.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.adapter.inbound.web.config.UseCaseConfig
import com.raynor.hexagonal.adapter.inbound.web.rest.adapter.UserRestController
import com.raynor.hexagonal.adapter.inbound.web.rest.dto.request.UserCreationRequestDto
import com.raynor.hexagonal.adapter.inbound.web.rest.dto.request.UserModificationRequestDto
import com.raynor.hexagonal.adapter.inbound.web.rest.mapper.UserDtoMapper
import com.raynor.hexagonal.application.port.inbound.usecase.*
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.test.UserFactory
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.personal.UserName
import io.kotest.core.spec.style.FunSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@ExtendWith(SpringExtension::class)
@Import(
    value = [
        UseCaseConfig::class,
        JacksonAutoConfiguration::class,
    ]
)
@SpringBootTest(
    classes = [
        UserRestController::class,
    ]
)
@EnableWebMvc
@AutoConfigureMockMvc
class UserRestControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockkBean private val readUsersUseCase: ReadUsersUseCase,
    @MockkBean private val readUserByIdUseCase: ReadUserByIdUseCase,
    @MockkBean private val createUserUseCase: CreateUserUseCase,
    @MockkBean private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    @MockkBean private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("[POST] /api/v1/users") {
        // given
        val user = UserFactory.create()
        val request = UserCreationRequestDto(
            email = user.personalInfo.email.value,
            name = user.personalInfo.name.value,
        )
        val expectedLocation = user.getURI().toString()

        // mock
        every {
            createUserUseCase.createUser(any())
        } returns user

        // when ~ then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.header().string("Location", expectedLocation))
    }

    test("[GET] /api/v1/users") {
        // given
        val users = listOf(
            UserFactory.create(),
            UserFactory.create(),
            UserFactory.create(),
        )
        val expectedResponse = objectMapper.writeValueAsString(
            users.map { UserDtoMapper.toUserResponseDto(it) }
        )

        // mock
        every {
            readUsersUseCase.readUsers(any())
        } returns users

        // when ~ then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
    }

    test("[GET] /api/v1/users/{userId}") {
        // given
        val user = UserFactory.create()
        val expectedResponse = objectMapper.writeValueAsString(
            UserDtoMapper.toUserDetailResponseDto(user)
        )

        // mock
        every {
            readUserByIdUseCase.readById(any())
        } returns user

        // when ~ then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/${user.id().value}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
    }

    test("[PATCH] /api/v1/users/{userId}") {
        // given
        val user = UserFactory.create()
        val request = UserModificationRequestDto(
            email = "changed-${user.personalInfo.email.value}",
            name = "changed-${user.personalInfo.name.value}",
            userStatus = UserStatus.LEFT,
        )

        val changedUser = user.copy(
            personalInfo = UserPersonalInfo(
                email = Email(request.email!!),
                name = UserName(request.name!!),
            ),
            userStatus = request.userStatus!!,
        )
        val expectedResponse = objectMapper.writeValueAsString(
            UserDtoMapper.toUserResponseDto(changedUser)
        )

        // mock
        every {
            updateUserByIdUseCase.updateUserById(any())
        } returns changedUser

        // when ~ then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/v1/users/${user.id().value}")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
    }

    test("[DELETE] /api/v1/users/{userId}") {
        // given
        val user = UserFactory.create()

        // mock
        every {
            deleteUserByIdUseCase.deleteUserById(any())
        } returns true

        // when ~ then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/${user.id().value}"))
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
    }
})