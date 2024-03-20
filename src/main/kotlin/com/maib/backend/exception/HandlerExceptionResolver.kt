package com.maib.backend.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler
    : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BasicException::class)
    fun handleBasicException(
        ex: BasicException,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity("Access denied message here", HttpHeaders(), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex:NotFoundException,
        request: WebRequest
    ):ResponseEntity<Any>{
        return ResponseEntity(ex.message, HttpHeaders(), HttpStatus.NOT_FOUND)
    }

}