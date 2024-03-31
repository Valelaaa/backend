package com.maib.backend.controller

import com.maib.backend.entity.comment.CommentDto
import com.maib.backend.entity.comment.CreateCommentDto
import com.maib.backend.service.comment.CommentService
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
class CommentController(
        private val commentService: CommentService
) {
    val log: Logger = LogManager.getLogger()

    @GetMapping("postId/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getCommentsByPostId(@PathVariable("postId") postId: String,
                            @RequestParam(name = "sortType", defaultValue = "date", required = false) sortType: String?,
                            @RequestParam("sortOrder", defaultValue = "asc", required = false) sortOrder: String?)
            : List<CommentDto> = commentService.findCommentsByPostId(postId, sortType, sortOrder)

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
    fun create(@RequestBody commentDto: CreateCommentDto) {
        log.info("Request to create comment with body $commentDto")
        commentService.create(commentDto)
    }


    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    fun update(@PathVariable("commentId") commentId: String,
               @RequestBody commentDto: CommentDto
    ) = commentService.update(commentId, commentDto)

}
