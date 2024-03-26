package com.maib.backend.controller

import com.maib.backend.entity.comment.CommentDto
import com.maib.backend.service.comment.CommentService
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
class CommentController(
        private val commentService: CommentService
) {


    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getCommentsByPostId(@PathVariable("postId") postId: String):List<CommentDto> = commentService.findCommentsByPostId(postId)
    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getByName(@PathVariable("commentId") commentId: String): CommentDto {
        return commentService.findById(commentId)
    }




    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Transactional
    fun delete(@PathVariable("commentId") commentId: String) = commentService.deleteById(commentId)

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun create(@RequestBody commentDto: CommentDto) = commentService.create(commentDto)


    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun update(@PathVariable("commentId") commentId: String,
               @RequestBody commentDto: CommentDto
    ) = commentService.update(commentId, commentDto)

}