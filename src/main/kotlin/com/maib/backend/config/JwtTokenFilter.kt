package com.maib.backend.config

import com.maib.backend.context.CurrentUserContext
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object JwtTokenFilter : OncePerRequestFilter() {
    private val secretKey: SecretKey = SecretKeySpec("open-mind-secret-key-secret-secret".toByteArray(), "HmacSHA256")

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = extractJwtToken(request)
        if (token != null) {
            try {
                val claims: Claims = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .body
                val userId = claims.subject
                CurrentUserContext.setCurrentUserId(userId)
            } catch (e: Exception) {
                e.printStackTrace()
                // Если произошла ошибка при проверке токена, то разрешаем доступ без авторизации
                // Здесь можно добавить дополнительную логику, если требуется
            }
        } else {
            // Если токен отсутствует, то разрешаем доступ без авторизации
            // Здесь можно добавить дополнительную логику, если требуется
        }
        filterChain.doFilter(request, response)
    }


    private fun extractJwtToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (!token.isNullOrBlank()) {
            token.substringAfter("Bearer ").takeIf { it.isNotBlank() }
        } else null
    }
}