package com.maib.backend.service.category

import com.maib.backend.entity.category.dto.CategoryDto
import com.maib.backend.exception.category.CategoryNotFoundException
import com.maib.backend.repository.CategoryRepository
import com.maib.backend.service.category.mapper.CategoryMapper
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val categoryMapper: CategoryMapper
) {
    fun findAll(): List<CategoryDto> {
        return categoryRepository.findAll().map(categoryMapper::dtoFromEntity)
    }

    fun findById(categoryName: String): CategoryDto {
        return categoryMapper.dtoFromEntity(categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName))
    }

    fun deleteById(categoryName: String) {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            categoryRepository.deleteByCategoryName(categoryName)
        } else {
            throw CategoryNotFoundException(categoryName)
        }
    }

    fun create(categoryDto: CategoryDto) {
        categoryRepository.save(
                categoryMapper.entityFromDto(categoryDto)
        )
    }

    fun update(categoryName: String, categoryDto: CategoryDto) {
        var currentCategory = categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName)
        currentCategory.categoryDescription = categoryDto.categoryDescription
        currentCategory.categoryTitle = categoryDto.categoryTitle
        currentCategory.tagLine = categoryDto.tagLine
        currentCategory.postCount = categoryDto.postCount
        if (categoryDto.categoryImage != null) {
            currentCategory.categoryImage = categoryMapper.saveImageToStorage(categoryDto.categoryImage)
        }

    }


}