package com.maib.backend.service.category.mapper

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.category.Category
import com.maib.backend.entity.category.dto.CategoryDto
import com.maib.backend.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryMapper(categoryRepository: CategoryRepository): Mapper<Category, CategoryDto> {
    override fun entityFromDto(dto: CategoryDto): Category {
        TODO("Not yet implemented")
    }

    override fun dtoFromEntity(entity: Category): CategoryDto {
        TODO("Not yet implemented")
    }

}
