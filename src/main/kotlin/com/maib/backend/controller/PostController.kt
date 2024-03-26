package com.maib.backend.controller

import com.maib.backend.entity.post.dto.CreatePostDto
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.service.post.PostService
import lombok.RequiredArgsConstructor
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
class PostController(
        private val postsService: PostService,
) {
    val log = LogManager.getLogger(PostController::class)

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getPosts(
            @RequestParam(name = "category", required = false) category: String?,
            @RequestParam(name = "sortType", required = false, defaultValue = "fresh") sortType: String,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") sortOrder: String,
    ): List<ShortPostDto> {
        log.info("Request to fetch $category posts")

        return postsService.getAllPosts(category, sortType, sortOrder)
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getPostById(
            @PathVariable("postId") postId: String
    ): PostDto {
        return postsService.findById(postId)
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createPost(@RequestBody postDto: CreatePostDto
    ) {
        log.info("Request to create new post $postDto")
        postsService.createPost(postDto)
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun updatePost(
            @PathVariable("postId") postId: String,
            @RequestParam postDto: PostDto
    ) {
        postsService.update(postId, postDto)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deletePost(
            @PathVariable("postId") postId: String
    ) {
        postsService.deletePost(postId)
    }
}