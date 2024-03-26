package com.maib.backend.service.category

import com.maib.backend.entity.category.CategoryDto
import com.maib.backend.exception.category.CategoryNotFoundException
import com.maib.backend.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val categoryMapper: CategoryMapper
) {
    fun findAll(sortType: String?, sortOrder: String?): List<CategoryDto> {
        var allCategories = categoryRepository.findAll().map(categoryMapper::dtoFromEntity)
        allCategories = when (sortType) {
            "name" -> allCategories.sortedBy { it.categoryName }
            "title" -> allCategories.sortedBy { it.categoryTitle }
            else -> allCategories
        }

        allCategories = if (sortOrder == "desc") {
            allCategories.reversed()
        } else {
            allCategories
        }

        return allCategories
    }

    fun finByName(categoryName: String): CategoryDto {
        return categoryMapper.dtoFromEntity(categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName))
    }

    @Transactional
    fun deleteByName(categoryName: String) {
        val category = categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName)
        categoryRepository.deleteById(category.categoryId)
    }

    fun create(categoryDto: CategoryDto) {
        categoryRepository.save(
                categoryMapper.entityFromDto(categoryDto)
        )
    }

    fun update(categoryName: String, categoryDto: CategoryDto) {
        val currentCategory = categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName)
        currentCategory.categoryDescription = categoryDto.categoryDescription
        currentCategory.categoryTitle = categoryDto.categoryTitle
        currentCategory.tagLine = categoryDto.tagLine
        currentCategory.postCount = categoryDto.postCount
        if (categoryDto.categoryImage != null) {
            currentCategory.categoryImage = categoryMapper.saveImageToStorage(categoryDto.categoryImage)
        }
        categoryRepository.save(currentCategory)
    }

    fun getPostCountByName(categoryName: String): Int {
        val category = categoryRepository.findByCategoryName(categoryName).getOrNull()
                ?: throw CategoryNotFoundException(categoryName)
        return category.postCount.toInt()
    }


}