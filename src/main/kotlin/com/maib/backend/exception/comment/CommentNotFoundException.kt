package com.maib.backend.exception.comment

import com.maib.backend.exception.NotFoundException

class CommentNotFoundException(commentId: String, message: String = "Comment with id = $commentId Not Found") : NotFoundException(message)
