package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.MileageMapper
import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.common.toPositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageId
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class WriteUserAdapterTest(
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val writeUserAdapter: WriteUserAdapter,
) : FunSpec({

    beforeEach {
        userRepository.deleteAllInBatch()
        mileageRepository.deleteAllInBatch()
    }

    test("신규 유저를 생성 할 수 있어야 한다.") {
        // given
        val mileageJPAEntity = mileageRepository.save(
            MileageJPAEntity(id = null, point = 0)
        )
        val user = User(
            email = Email("new@dev.com"),
            name = UserName("new_user_1"),
        )
        user.mileage = MileageMapper.toDomain(mileageJPAEntity)

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
            UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
        )
        val userModification = UserModification(
            UserName("modified_user_1@dev.com"),
            Email("modified_user_1"),
            UserStatus.LEFT
        )
        val user = UserMapper.toDomain(userJPAEntity)
        user.update(userModification)

        val expectedUser = User(
            id = UserId(userJPAEntity.id!!),
            personalInfo = UserPersonalInfo(userModification.email!!, userModification.name!!),
            userStatus = userModification.userStatus!!,
            audit = Audit(
                createdAt = userJPAEntity.createdAt,
                updatedAt = userJPAEntity.updatedAt,
            ),
            mileage = Mileage(
                id = MileageId(userJPAEntity.mileage.id!!),
                point = userJPAEntity.mileage.point.toPositiveOrZeroInt()
            )
        )

        // when ~ then
        writeUserAdapter.update(user).also {
            it.shouldBeEqualToIgnoringFields(
                expectedUser,
                User::audit
            )
            it.audit.createdAt shouldBe expectedUser.audit.createdAt
            it.audit.updatedAt shouldNotBe expectedUser.audit.updatedAt
        }
    }

    test("유저 삭제를 할 수 있어야 한다.") {
        // given
        val userJPAEntity = userRepository.save(
            UserFactory.generalUserJPAEntity().also { mileageRepository.save(it.mileage) },
        )
        val user = UserMapper.toDomain(userJPAEntity)

        // when
        writeUserAdapter.delete(user)

        // then
        userRepository.findByIdOrNull(userJPAEntity.id) shouldBe null
    }
})
