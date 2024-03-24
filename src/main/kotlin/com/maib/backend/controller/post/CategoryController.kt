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

    @GetMapping("/{categoryName}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getByName(@PathVariable("categoryName") categoryName: String): CategoryDto {
        return categoryService.findById(categoryName)
    }


    @DeleteMapping("/{categoryName}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteById(@PathVariable("categoryName") categoryName: String) = categoryService.deleteById(categoryName)

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun create(@RequestBody categoryDto: CategoryDto) = categoryService.create(categoryDto)


    @PutMapping("/{categoryName}")
    @ResponseStatus(value = HttpStatus.OK)
    fun update(@PathVariable("categoryName") categoryName: String, @RequestBody categoryDto: CategoryDto) = categoryService.update(categoryName, categoryDto)
}