package com.maib.backend.exception.user

import com.maib.backend.exception.NotFoundException

class UserNotFoundException(userId: String, message: String = "User with id = $userId Not Found") : NotFoundException(message) {

}