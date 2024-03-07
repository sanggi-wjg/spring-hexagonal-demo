package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
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
        val userCreation = UserCreation("user_1@dev.com", "user_1")

        // when
        writeUserAdapter.saveNewUser(userCreation).also {
            // then
            User.of(userRepository.findByIdOrNull(it.id)!!) shouldBe it
        }
    }

    test("유저 정보를 변경할 수 있어야 한다.") {
        // given
        val userEntity = userRepository.save(UserEntity("user_1@dev.com", "user_1"))
        val userModification = UserModification("modified_user_1@dev.com", "modified_user_1", UserStatus.LEFT)
        val expectedUser = User(
            id = userEntity.id!!,
            email = userModification.email!!,
            name = userModification.name!!,
            userStatus = userModification.userStatus!!
        )

        // when
        writeUserAdapter.updateUserById(userEntity.id!!, userModification).also {
            // then
            it!!.shouldBeEqualToComparingFields(expectedUser)
        }
    }

    test("없는 유저 정보는 변경하지 않는다.") {
        // given
        val userModification = UserModification("modified_user_1@dev.com", "modified_user_1", UserStatus.LEFT)

        // when
        writeUserAdapter.updateUserById(0, userModification) shouldBe null
    }

    test("유저 삭제를 할 수 있어야 한다.") {
        // given
        val userEntity = userRepository.save(UserEntity("user_1@dev.com", "user_1"))

        // when
        writeUserAdapter.deleteUserById(userEntity.id!!)

        // then
        userRepository.findByIdOrNull(userEntity.id) shouldBe null
    }
})
