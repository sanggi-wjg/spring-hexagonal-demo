package com.example.springbootkotlinhexagonaldemo.infrastructure.controller

import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.ReadUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserCreationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserModificationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

        return ResponseEntity(
            writeUserEndpointPort.createUser(command),
            HttpStatus.CREATED,
        )
    }

    @GetMapping("")
    fun getAllUsers(
        @RequestParam("userId", required = false) userIds: List<Int>?
    ): ResponseEntity<Collection<UserResponseDto>> {
        val query = ReadUsersUseCase.Query(
            userIds = userIds?.map { UserId(it) },
        )

        return ResponseEntity(
            readUserEndpointPort.getAllUsers(query),
            HttpStatus.OK,
        )
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<UserResponseDto> {
        val query = ReadUserByIdUseCase.Query(
            UserId(userId),
        )

        return ResponseEntity(
            readUserEndpointPort.getUserById(query),
            HttpStatus.OK
        )
    }

    @PatchMapping("/{userId}")
    fun modifyUserById(
        @PathVariable("userId") userId: Int,
        @RequestBody @Valid userModificationDto: UserModificationDto,
    ): ResponseEntity<UserResponseDto> {
        val command = userModificationDto.toUpdateUserByIdUseCaseCommand(userId)

        return ResponseEntity(
            writeUserEndpointPort.modifyUserById(command),
            HttpStatus.OK
        )
    }

    @DeleteMapping("/{userId}")
    fun removeUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<Unit> {
        val command = DeleteUserByIdUseCase.Command(
            UserId(userId),
        )

        writeUserEndpointPort.removeUserById(command)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
