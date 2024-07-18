package com.raynor.hexagonal.adapter.inbound.web.rest.adapter

import com.raynor.hexagonal.adapter.inbound.web.rest.dto.request.UserCreationRequestDto
import com.raynor.hexagonal.adapter.inbound.web.rest.dto.request.UserModificationRequestDto
import com.raynor.hexagonal.adapter.inbound.web.rest.dto.response.UserDetailResponseDto
import com.raynor.hexagonal.adapter.inbound.web.rest.dto.response.UserResponseDto
import com.raynor.hexagonal.adapter.inbound.web.rest.mapper.UserDtoMapper
import com.raynor.hexagonal.application.port.inbound.usecase.*
import com.raynor.hexagonal.domain.type.id.UserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserRestController(
    private val readUsersUseCase: ReadUsersUseCase,
    private val readUserByIdUseCase: ReadUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
) {

    @PostMapping("")
    fun createUser(
        @RequestBody @Valid userCreationRequestDto: UserCreationRequestDto,
    ): ResponseEntity<UserResponseDto> {
        val command = userCreationRequestDto.toCreateUserUseCaseCommand()

        return createUserUseCase.createUser(command).let {
            ResponseEntity.created(it.getURI()).build()
        }
    }

    @GetMapping("")
    fun getAllUsers(
        @RequestParam("userId", required = false) userIds: List<Int>?
    ): ResponseEntity<Collection<UserResponseDto>> {
        val query = ReadUsersUseCase.Query(
            userIds = userIds?.map { UserId(it) },
        )
        return readUsersUseCase.readUsers(query).let { users ->
            ResponseEntity.ok(users.map { UserDtoMapper.toUserResponseDto(it) })
        }
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<UserDetailResponseDto> {
        val query = ReadUserByIdUseCase.Query(
            UserId(userId),
        )

        return readUserByIdUseCase.readById(query).let {
            ResponseEntity.ok(UserDtoMapper.toUserDetailResponseDto(it))
        }
    }

    @PatchMapping("/{userId}")
    fun modifyUserById(
        @PathVariable("userId") userId: Int,
        @RequestBody @Valid userModificationRequestDto: UserModificationRequestDto,
    ): ResponseEntity<UserResponseDto> {
        val command = userModificationRequestDto.toUpdateUserByIdUseCaseCommand(userId)

        return updateUserByIdUseCase.updateUserById(command).let {
            ResponseEntity.ok(UserDtoMapper.toUserResponseDto(it))
        }
    }

    @DeleteMapping("/{userId}")
    fun removeUserById(
        @PathVariable("userId") userId: Int,
    ): ResponseEntity<Unit> {
        val command = DeleteUserByIdUseCase.Command(
            UserId(userId),
        )

        return deleteUserByIdUseCase.deleteUserById(command).let {
            ResponseEntity.noContent().build()
        }
    }
}
