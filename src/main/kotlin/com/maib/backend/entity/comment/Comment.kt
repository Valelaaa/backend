package com.maib.backend.entity.comment

import com.maib.backend.entity.post.Post
import com.maib.backend.entity.profile.Profile
import com.maib.backend.entity.rating.Rating
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "comments")
data class Comment(
        @Id
        @Column(name = "comment_id")
        var commentId: String = UUID.randomUUID().toString(),
        @ManyToOne
        @PrimaryKeyJoinColumn(name = "comment_author_id")
        var commentAuthor: Profile = Profile(),
        @Column(name = "comment_message")
        var message: String = "",
        @Column(name = "created_date")
        var createdDate: Date = Date(System.currentTimeMillis()),
        @ManyToOne
        @PrimaryKeyJoinColumn(name = "post_id")
        var post: Post = Post(),
        @ManyToOne
        @JoinColumn(name = "parent_comment_id")
        var parentComment: Comment? = null,
        @OneToOne
        @PrimaryKeyJoinColumn(name = "rating_id")
        var rating: Rating = Rating(),
        @OneToMany(mappedBy = "parentComment")
        var subComments: List<Comment>? = null,
) {
    fun getCommentCount() = subComments?.size?.plus(1) ?: 1
}