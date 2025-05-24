package com.maib.backend.config

import com.maib.backend.entity.category.dto.CategoryDto
import com.maib.backend.entity.post.dto.PostDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    summary = "Create Category",
    requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Endpoint to create a category",
        required = true,
        content = [Content(
            schema = Schema(implementation = CategoryDto::class),
            examples = [ExampleObject(
                name = "Example",
                value = """
                    {
                      "categoryName": "My Category",
                      "categoryTitle": "",
                      "categoryImage": null,
                      "categoryDescription": "",
                      "tagLine": ""
                    }
                    """
            )]
        )]
    )
)
annotation class CreateCategorySwaggerRequest

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    summary = "Create a Post",
    requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Endpoint to create a post",
        required = true,
        content = [Content(
            schema = Schema(implementation = PostDto::class),
            examples = [ExampleObject(
                name = "Post",
                value = """
                    {
                        "title": "string",
                        "description": "string",
                        "createdDate": "2025-05-23T19:32:33.209Z",
                        "creatorName": "string",
                        "category": "string"
                    }
                """
            )]
        )]
    )
)
annotation class CreatePostSwaggerRequest

