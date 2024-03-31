package com.maib.backend.controller

import com.maib.backend.service.JwtTokenService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
class UserController(private val tokenService: JwtTokenService) {


    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun login(@RequestParam("profileId") profileId: String): String {
        return tokenService.generateToken(profileId)
    }


}