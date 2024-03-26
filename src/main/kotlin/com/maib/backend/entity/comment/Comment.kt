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

        @Column(name = "comment_message")
        var message: String = "",

        @Column(name = "created_date")
        var createdDate: Date = Date(System.currentTimeMillis()),

        @ManyToOne
        @JoinColumn(name = "profile_id")
        val profile: Profile = Profile(),

        @ManyToOne
        @JoinColumn(name = "post_id")
        var post: Post? = Post(),

        @OneToOne
        @JoinColumn(name = "rating_id")
        var rating: Rating = Rating(),

        @ManyToOne
        @JoinColumn(name = "parent_comment_id", nullable = true)
        var parent: Comment? = null,

        @OneToMany(mappedBy = "parent")
        var subcomments: MutableList<Comment> = mutableListOf()
) {
}