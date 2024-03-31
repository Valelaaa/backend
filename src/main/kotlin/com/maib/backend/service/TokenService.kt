package com.maib.backend.service

interface TokenService {
    fun generateToken(userId: String): String
}
