package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.mapper

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.scheme.MileageBasicScheme
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.scheme.MileageHistoryBasicScheme
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.MileageHistory
import com.example.springbootkotlinhexagonaldemo.domain.entity.User

object UserDtoMapper {

    fun toUserResponseDto(user: User) = UserResponseDto(
        id = user.id!!.value,
        email = user.personalInfo.email.value,
        name = user.personalInfo.name.value,
        userStatus = user.userStatus,
        createdAt = user.audit.createdAt,
        updatedAt = user.audit.updatedAt,
    )

    fun toUserDetailResponseDto(user: User) = UserDetailResponseDto(
        id = user.id!!.value,
        email = user.personalInfo.email.value,
        name = user.personalInfo.name.value,
        userStatus = user.userStatus,
        createdAt = user.audit.createdAt,
        updatedAt = user.audit.updatedAt,
        mileage = toMileageScheme(user.mileage),
        mileageHistories = user.mileageHistories.map { toMileageHistoryResponseDto(it) },
    )

    private fun toMileageScheme(mileage: Mileage) = MileageBasicScheme(
        id = mileage.id!!.value,
        point = mileage.point.value
    )

    private fun toMileageHistoryResponseDto(mileageHistory: MileageHistory) = MileageHistoryBasicScheme(
        id = mileageHistory.id!!.value,
        beforePoint = mileageHistory.beforePoint.value,
        afterPoint = mileageHistory.afterPoint.value,
        point = mileageHistory.point,
        message = mileageHistory.message,
        createdAt = mileageHistory.audit.createdAt,
        updatedAt = mileageHistory.audit.updatedAt,
    )
}