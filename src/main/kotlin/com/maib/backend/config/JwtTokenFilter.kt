package com.maib.backend.config

import com.maib.backend.context.CurrentUserContext
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


object JwtTokenFilter : OncePerRequestFilter() {
    private val secretKey: SecretKey = SecretKeySpec("open-mind-secret-key-secret-secret".toByteArray(), "HmacSHA256")
    private val log = LogManager.getLogger(JwtTokenFilter::class.java)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val wrappedRequest = ContentCachingRequestWrapper(request)
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
        filterChain.doFilter(wrappedRequest, response)
    }


    private fun extractJwtToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (!token.isNullOrBlank()) {
            token.substringAfter("Bearer ").takeIf { it.isNotBlank() }
        } else null
    }
}