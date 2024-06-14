package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class WriteUserAdapterTest(
    private val userRepository: UserRepository,
    private val writeUserAdapter: WriteUserAdapter,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
    }

    test("신규 유저를 생성 할 수 있어야 한다.") {
        // given
        val user = User(
            email = Email("user_1@dev.com"),
            name = UserName("user_1"),
        )

        // when
        val saveUser = writeUserAdapter.create(user)

        // then
        userRepository.findByIdOrNull(saveUser.id!!.value)!!.also { findUser ->
            UserMapper.toDomain(findUser).shouldBeEqualToComparingFields(saveUser)
        }
    }

    test("유저 정보를 변경할 수 있어야 한다.") {
        // given
        val userJPAEntity = userRepository.save(
            UserJPAEntity(id = null, email = "user_1@dev.com", name = "user_11", userStatus = UserStatus.ACTIVE),
        )
        val userModification = UserModification(
            UserName("modified_user_1@dev.com"), Email("modified_user_1"), UserStatus.LEFT
        )

        val user = UserMapper.toDomain(userJPAEntity)
        user.update(userModification)

        val expectedUser = User(
            id = UserId(userJPAEntity.id!!),
            personalInfo = UserPersonalInfo(userModification.email!!, userModification.name!!),
            userStatus = userModification.userStatus!!
        )

        // when ~ then
        writeUserAdapter.update(user).also {
            it.shouldBeEqualToComparingFields(expectedUser)
        }
    }

    test("유저 삭제를 할 수 있어야 한다.") {
        // given
        val userJPAEntity = userRepository.save(
            UserJPAEntity(id = null, email = "user_1@dev.com", name = "user_11", userStatus = UserStatus.ACTIVE),
        )
        val user = UserMapper.toDomain(userJPAEntity)

        // when
        writeUserAdapter.delete(user)

        // then
        userRepository.findByIdOrNull(userJPAEntity.id) shouldBe null
    }
})
