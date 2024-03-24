package com.maib.backend.exception.category

import com.maib.backend.exception.NotFoundException

class CategoryNotFoundException(categoryName: String, message: String = "Post with name = $categoryName Not Found") :
    NotFoundException(message)
