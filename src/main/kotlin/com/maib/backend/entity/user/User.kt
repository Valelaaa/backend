package com.maib.backend.entity.user

import com.maib.backend.entity.profile.Profile
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import lombok.Builder
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@Builder
data class User(
    @Id
    var userId:String = UUID.randomUUID().toString(),
    var nickname:String ="",
    var firstName:String ="",
    var lastName:String = "",
    var email:String ="",
    var phoneNumber:String ="",
    var password: String ="",
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    var profile:Profile? = null
)