package com.example.springbootkotlinhexagonaldemo.infrastructure.controller

import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.ReadUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserCreationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.UserModificationDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request.toDomain
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
        return ResponseEntity(
            writeUserEndpointPort.createUser(userCreationDto.toDomain()),
            HttpStatus.CREATED,
        )
    }

    @GetMapping("")
    fun getAllUsers(): ResponseEntity<Collection<UserResponseDto>> {
        return ResponseEntity(
            readUserEndpointPort.getAllUsers(),
            HttpStatus.OK,
        )
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity(
            readUserEndpointPort.getUserById(userId),
            HttpStatus.OK
        )
    }

    @PatchMapping("/{userId}")
    fun modifyUserById(
        @PathVariable("userId") userId: Int,
        @RequestBody @Valid userModificationDto: UserModificationDto,
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity(
            writeUserEndpointPort.modifyUserById(userId, userModificationDto.toDomain()),
            HttpStatus.OK
        )
    }

    @DeleteMapping("/{userId}")
    fun removeUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<Unit> {
        writeUserEndpointPort.removeUserById(userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
