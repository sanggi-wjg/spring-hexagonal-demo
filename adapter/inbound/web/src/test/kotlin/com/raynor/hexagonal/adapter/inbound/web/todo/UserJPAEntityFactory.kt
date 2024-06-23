package com.raynor.hexagonal.adapter.inbound.web.todo

//class UserJPAEntityFactory(
//    private val userRepository: UserRepository,
//    private val mileageRepository: MileageRepository,
//    private val mileageHistoryRepository: MileageHistoryRepository,
//) {
//
//    fun create(
//        userStatus: UserStatus = UserStatus.ACTIVE,
//        mileagePoint: Int = 0,
//    ): UserJPAEntity {
//        val uuid = UUID.randomUUID()
//
//        return userRepository.save(
//            UserJPAEntity(
//                id = null,
//                email = "$uuid@dev.com",
//                name = "user_$uuid",
//                userStatus = userStatus,
//                createdAt = Instant.now(),
//                updatedAt = Instant.now(),
//                mileage = mileageRepository.save(3
//                    MileageJPAEntity(id = null, point = mileagePoint)
//                )
//            )
//        )
//    }
//
//    fun deleteAll() {
//        listOf(
//            userRepository,
//            mileageHistoryRepository,
//            mileageRepository,
//        ).forEach {
//            it.deleteAllInBatch()
//        }
//    }
//}