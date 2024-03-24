package com.maib.backend.controller.post

import com.maib.backend.entity.category.dto.CategoryDto
import com.maib.backend.service.category.CategoryService
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
    fun getAll(): List<CategoryDto> {
        return categoryService.findAll()
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getById(@PathVariable("categoryId") categoryId: String): CategoryDto {
        return categoryService.findById(categoryId)
    }


    @DeleteMapping("/{categoryId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteById(@PathVariable("categoryId") categoryId: String) = categoryService.deleteById(categoryId)

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun create(@RequestBody categoryDto: CategoryDto) = categoryService.create(categoryDto)


    @PutMapping("/{categoryId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun update(@PathVariable("categoryId") categoryId: String, @RequestBody categoryDto: CategoryDto) = categoryService.update(categoryId, categoryDto)
}