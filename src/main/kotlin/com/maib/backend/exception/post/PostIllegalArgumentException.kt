package com.maib.backend.exception.post

class PostIllegalArgumentException(message: String?) : IllegalArgumentException(message ?: "Wrong Argument") {
}