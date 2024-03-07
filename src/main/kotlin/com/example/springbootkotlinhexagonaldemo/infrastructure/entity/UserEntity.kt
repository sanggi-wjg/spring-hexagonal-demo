package com.example.springbootkotlinhexagonaldemo.infrastructure.entity

import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "user")
open class UserEntity {
    constructor(
        email: String,
        name: String,
    ) {
        this.email = email
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 254)
    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 254)
    open var email: String? = null

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    open var name: String? = null

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 64)
    open var userStatus: UserStatus = UserStatus.ACTIVE
}
