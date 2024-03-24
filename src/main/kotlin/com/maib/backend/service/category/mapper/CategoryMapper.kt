package com.maib.backend.service.category.mapper

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.category.Category
import com.maib.backend.entity.category.dto.CategoryDto
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
                categoryImage = getImageFromPath(entity.categoryImage)
        )
    }

    fun saveImageToStorage(imageByteArray: ByteArray?): String? {
        if (imageByteArray == null) {
            return null
        }

        val timestamp = Instant.now().toEpochMilli()
        val uniqueImageName = "image-$timestamp"

        val storageDir = File("storage")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        val imageFile = File(storageDir, "$uniqueImageName.jpg")
        val outputStream = FileOutputStream(imageFile)
        outputStream.use {
            outputStream.write(imageByteArray)
            outputStream.flush()
        }

        return imageFile.absolutePath
    }

    fun getImageFromPath(imagePath: String?): ByteArray? {
        if (imagePath == null) {
            return null
        }

        val imageFile = File(imagePath)
        if (!imageFile.exists()) {
            return null
        }
        return try {
            Files.readAllBytes(imageFile.toPath())
        } catch (e: Exception) {
            null
        }
    }
}
