package com.maib.backend.service.category

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.category.Category
import com.maib.backend.entity.category.CategoryDto
import com.maib.backend.exception.category.CategoryNotFoundException
import com.maib.backend.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.time.Instant
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryMapper(
        private val categoryRepository: CategoryRepository
) : Mapper<Category, CategoryDto> {
    override fun entityFromDto(dto: CategoryDto): Category {
        return if (categoryRepository.existsByCategoryName(dto.categoryName)) {

            categoryRepository.findByCategoryName(dto.categoryName).getOrNull()
                    ?: throw CategoryNotFoundException(categoryName = dto.categoryName)

        } else {

            Category(
                    categoryId = UUID.randomUUID().toString(),
                    categoryName = dto.categoryName,
                    categoryTitle = dto.categoryTitle,
                    categoryDescription = dto.categoryDescription,
                    tagLine = dto.tagLine,
                    postCount = dto.postCount,
                    categoryImage = saveImageToStorage(dto.categoryImage)
            )

        }
    }

    override fun dtoFromEntity(entity: Category): CategoryDto {
        return CategoryDto(
                categoryName = entity.categoryName,
                categoryTitle = entity.categoryTitle,
                categoryDescription = entity.categoryDescription,
                tagLine = entity.tagLine,
                postCount = entity.postCount,
                categoryImage = getImageFromPathAsBase64(entity.categoryImage)
        )
    }

    fun saveImageToStorage(imageBase64: String?): String? {
        if (imageBase64 == null) {
            return null
        }

        val imageBytes = Base64.getDecoder().decode(imageBase64)

        val timestamp = Instant.now().toEpochMilli()
        val uniqueImageName = "image-$timestamp"

        val storageDir = File("/app/storage")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        val imageFile = File(storageDir, "$uniqueImageName.jpg")
        val outputStream = FileOutputStream(imageFile)
        outputStream.use {
            outputStream.write(imageBytes)
            outputStream.flush()
        }

        return imageFile.absolutePath
    }

    fun getImageFromPathAsBase64(imagePath: String?): String? {
        if (imagePath == null) {
            return null
        }

        val imageFile = File(imagePath)
        if (!imageFile.exists()) {
            return null
        }
        return try {
            Base64.getEncoder().encodeToString(Files.readAllBytes(imageFile.toPath()))
        } catch (e: Exception) {
            null
        }
    }

}
