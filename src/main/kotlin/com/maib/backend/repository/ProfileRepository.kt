package com.maib.backend.repository

import com.maib.backend.entity.profile.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository: JpaRepository<Profile, String> {
}