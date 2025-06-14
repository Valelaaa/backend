package com.maib.backend.controller

import com.maib.backend.config.CreatePostSwaggerRequest
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.service.post.PostService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
class PostController(
    private val postsService: PostService,
) {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getPosts(
        @RequestParam(name = "category", required = false) category: String?,
        @RequestParam(name = "sortType", required = false, defaultValue = "fresh") sortType: String,
        @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") sortOrder: String,
    ): List<ShortPostDto> {
        return postsService.getAllPosts(category, sortType, sortOrder)
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getPostById(@PathVariable("postId") postId: String, ): PostDto {
        return postsService.findById(postId)
    }

    @PostMapping
    @CreatePostSwaggerRequest
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createPost(@RequestBody postDto: PostDto, ) {
        postsService.createPost(postDto)
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun updatePost(
        @PathVariable("postId") postId: String,
        @RequestParam postDto: PostDto,
    ) {
        postsService.update(postId, postDto)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deletePost(@PathVariable("postId") postId: String, ) {
        postsService.deletePost(postId)
    }
}