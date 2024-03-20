package com.maib.backend.exception.category

import com.maib.backend.exception.NotFoundException

class CategoryNotFoundException(categoryId: String, message: String = "Post with id = $categoryId Not Found") :
    NotFoundException(message)
