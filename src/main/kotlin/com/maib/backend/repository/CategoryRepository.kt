package com.maib.backend.repository

import com.maib.backend.entity.category.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, String> {

    fun findByCategoryName(name: String): Optional<Category>
}