package com.maib.backend.controller.post

import com.maib.backend.entity.post.dto.PostMapper
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.entity.post.dto.ShortPostMapper
import com.maib.backend.service.post.PostService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
class PostController(
    val postsService: PostService,
    val postMapper: PostMapper,
    val shortPostMapper: ShortPostMapper
) {
    @GetMapping
    fun getPosts(
        @RequestParam(name = "profileId", required = false) profileId: String?,
        @RequestParam(name = "category", required = false) category: String?,
        @RequestParam(name = "sortType", required = false, defaultValue = "fresh") sortType: String,
        @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") sortOrder: String,
    ): List<ShortPostDto> {
        return postsService.getAllPosts(category, sortType, sortOrder).map { post ->
            if (profileId != null) {
                shortPostMapper.dtoFromEntityPersonalized(post, profileId)
            } else {
                shortPostMapper.dtoFromEntity(post)
            }
        }
    }

}