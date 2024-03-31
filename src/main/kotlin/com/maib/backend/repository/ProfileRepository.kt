package com.maib.backend.repository

import com.maib.backend.entity.profile.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileRepository: JpaRepository<Profile, String> {
    fun findProfileByUserUserId(userId:String): Optional<Profile>
}