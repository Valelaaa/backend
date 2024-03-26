package com.maib.backend.entity.rating

import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.profile.Profile
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "ratings")
data class Rating(
        @Id
        @Column(name = "rating_id")
        val ratingId: String = UUID.randomUUID().toString(),

        var ratingValue: Int = 0,

        @OneToOne(mappedBy = "rating", cascade = [CascadeType.ALL])
        var post: Post? = null,

        @OneToOne(mappedBy = "rating", cascade = [CascadeType.ALL])
        var comment: Comment? = null,

        @ManyToMany(mappedBy = "ratings")
        val profiles: MutableList<Profile> = mutableListOf()
)