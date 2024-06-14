package com.example.springbootkotlinhexagonaldemo.infrastructure.entity

import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "user")
class UserJPAEntity(
    id: Int?,
    email: String,
    name: String,
    userStatus: UserStatus,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = id
        private set

    @Size(max = 254)
    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 254)
    var email: String = email
        private set

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    var name: String = name
        private set

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 64)
    var userStatus: UserStatus = userStatus
        private set
}
