package com.example.springbootkotlinhexagonaldemo.adapter.endpoint.mapper

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

object UserDtoMapper {

    fun toUserResponseDto(user: User) = UserResponseDto(
        id = user.id!!.value,
        email = user.personalInfo.email.value,
        name = user.personalInfo.name.value,
        userStatus = user.userStatus,
    )
}