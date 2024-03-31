package com.maib.backend.service

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Service
class JwtTokenService : TokenService {
    private val secretKey: SecretKey = SecretKeySpec("open-mind-secret-key-secret-secret".toByteArray(), "HmacSHA256")

    override fun generateToken(userId: String): String {
        val now = Date(System.currentTimeMillis())
        val expirationDate = Date(now.time + 60 * 60 * 24 * 14 * 1000)

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact()
    }

}
