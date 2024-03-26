package com.maib.backend.controller

import com.maib.backend.entity.category.CategoryDto
import com.maib.backend.service.category.CategoryService
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
class CategoryController(
        private val categoryService: CategoryService
) {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getAll(
            @RequestParam(name = "sortType", required = false, defaultValue = "name") sortType: String?,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") sortOrder: String?,
    ): List<CategoryDto> {
        return categoryService.findAll(sortType, sortOrder)
    }

    @GetMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getByName(@PathVariable("name") categoryName: String): CategoryDto {
        return categoryService.finByName(categoryName.uppercase())
    }

    @GetMapping("/count")
    @ResponseStatus(value = HttpStatus.OK)
    fun getPostCount(
            @RequestParam("name") categoryName: String
    ): Int = categoryService.getPostCountByName(categoryName.uppercase())

    @DeleteMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    @Transactional
    fun delete(@PathVariable("name") categoryName: String) = categoryService.deleteByName(categoryName.uppercase())

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun create(@RequestBody categoryDto: CategoryDto) = categoryService.create(categoryDto)

    @PutMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    fun update(@PathVariable("name") categoryName: String, @RequestBody categoryDto: CategoryDto) = categoryService.update(categoryName.uppercase(), categoryDto)
}