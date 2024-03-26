package com.maib.backend.service.post

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.PostRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class PostMapper(private val postRepository: PostRepository,
                 private val commentRepository: CommentRepository) :
        Mapper<Post, PostDto> {

    override fun entityFromDto(dto: PostDto): Post {
        return postRepository.findById(dto.postId)
                .getOrNull() ?: throw PostNotFoundException(dto.postId)
    }

    override fun dtoFromEntity(entity: Post): PostDto {
        val comments = commentRepository.findCommentsByPost_PostId(entity.postId)
        var commentCount = comments.count()
        comments.map {
            it.subcomments.forEach { _ ->
                commentCount += 1
            }
        }
        return PostDto(
                postId = entity.postId,
                title = entity.title,
                description = entity.description,
                createdDate = entity.createdDate,
                creatorName = entity.profile.user.nickname,
                isRated = 0,
                rating = entity.rating.ratingValue,
                category = entity.category.categoryName,
                commentCount = commentCount
        )
    }

}