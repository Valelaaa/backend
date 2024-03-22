package com.maib.backend.entity.rating

import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.post.Post
import jakarta.persistence.*
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "ratings")
data class Rating(
    @Id
    @Column(name= "rating_id")
    val ratingId: String = UUID.randomUUID().toString(),

    @OneToOne(cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn(name = "comment_id")
    val comment: Comment? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn(name = "post_id")
    val post: Post? = null,
    @Column(name = "rating_value")
    val ratingValue: Int = 0
)