package com.maib.backend.entity.user

import com.maib.backend.entity.profile.Profile
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
data class User(
        @Id
        @Column(name = "user_id")
        var userId: String = UUID.randomUUID().toString(),
        @Column(name = "nickname", nullable = false, unique = true)
        var nickname: String = "janedoe",
        @Column(name = "first_name")
        var firstName: String = "Jane",
        @Column(name = "last_name")
        var lastName: String = "Doe",
        @Column(name = "email")
        var email: String = "@mail",
        @Column(name = "phone_number")
        var phoneNumber: String = "67547454",
        @Column(name = "password")
        var password: String = "*",
)