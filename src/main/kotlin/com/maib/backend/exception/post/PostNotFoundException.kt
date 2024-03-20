package com.maib.backend.exception.post

import com.maib.backend.exception.NotFoundException

class PostNotFoundException(postId: String, message: String = "Post with id = $postId Not Found") : NotFoundException(message)