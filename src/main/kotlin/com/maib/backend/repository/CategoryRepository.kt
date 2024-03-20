package com.maib.backend.repository

import com.maib.backend.entity.category.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, String> {
}