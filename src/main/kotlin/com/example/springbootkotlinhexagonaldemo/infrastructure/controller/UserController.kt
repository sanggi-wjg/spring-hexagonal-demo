package com.example.springbootkotlinhexagonaldemo.infrastructure.controller

import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.ReadUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserCreationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserModificationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(
    private val readUserEndpointPort: ReadUserEndpointPort,
    private val writeUserEndpointPort: WriteUserEndpointPort,
) {

    @PostMapping("")
    fun createUser(
        @RequestBody @Valid userCreationDto: UserCreationDto,
    ): ResponseEntity<UserResponseDto> {
        val command = userCreationDto.toCreateUserUseCaseCommand()

        return writeUserEndpointPort.createUser(command).let {
            ResponseEntity.created(URI.create("/users/${it.id}")).build()
        }
    }

    @GetMapping("")
    fun getAllUsers(
        @RequestParam("userId", required = false) userIds: List<Int>?
    ): ResponseEntity<Collection<UserResponseDto>> {
        val query = ReadUsersUseCase.Query(
            userIds = userIds?.map { UserId(it) },
        )

        return readUserEndpointPort.readUsers(query).let {
            ResponseEntity.ok(it)
        }
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<UserDetailResponseDto> {
        val query = ReadUserByIdUseCase.Query(
            UserId(userId),
        )

        return readUserEndpointPort.readUserById(query).let {
            ResponseEntity.ok(it)
        }
    }

    @PatchMapping("/{userId}")
    fun modifyUserById(
        @PathVariable("userId") userId: Int,
        @RequestBody @Valid userModificationDto: UserModificationDto,
    ): ResponseEntity<UserResponseDto> {
        val command = userModificationDto.toUpdateUserByIdUseCaseCommand(userId)

        return writeUserEndpointPort.updateUserById(command).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{userId}")
    fun removeUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<Unit> {
        val command = DeleteUserByIdUseCase.Command(
            UserId(userId),
        )

        return writeUserEndpointPort.deleteUserById(command).let {
            ResponseEntity.noContent().build()
        }
    }
}
