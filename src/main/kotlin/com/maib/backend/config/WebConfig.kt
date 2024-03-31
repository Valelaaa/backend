package com.maib.backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import jakarta.servlet.Filter
import org.springframework.context.annotation.Bean

@Configuration
class WebConfig : WebMvcConfigurer {

    // Регистрируем фильтр в качестве бина
    @Bean
    fun jwtTokenFilter(): Filter {
        return JwtTokenFilter
    }
}