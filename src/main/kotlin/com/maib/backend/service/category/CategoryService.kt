package com.maib.backend.service.category

import com.maib.backend.entity.category.dto.CategoryDto
import com.maib.backend.repository.CategoryRepository
import com.maib.backend.service.category.mapper.CategoryMapper
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val caegoryMapper: CategoryMapper
) {
    fun findAll(): List<CategoryDto> {
        TODO("Not yet implemented")
    }

    fun findById(categoryId: String): CategoryDto {
        TODO()
    }

    fun deleteById(categoryId: String) {

    }

    fun create(categoryDto: CategoryDto) {

    }

    fun update(categoryId: String, categoryDto: CategoryDto) {

    }
}