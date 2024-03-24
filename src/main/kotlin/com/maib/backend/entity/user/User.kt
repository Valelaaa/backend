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
        var nickname: String = "",
        @Column(name = "first_name")
        var firstName: String = "",
        @Column(name = "last_name")
        var lastName: String = "",
        @Column(name = "email")
        var email: String = "",
        @Column(name = "phone_number")
        var phoneNumber: String = "",
        @Column(name = "password")
        var password: String = "",
        @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
        var profile: Profile? = null
)