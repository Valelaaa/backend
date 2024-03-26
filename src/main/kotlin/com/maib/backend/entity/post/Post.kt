package com.maib.backend.entity.post


import com.maib.backend.entity.category.Category
import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.profile.Profile
import com.maib.backend.entity.rating.Rating
import jakarta.persistence.*
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "posts")
data class Post(
    @Id
    @Column(name = "post_id", nullable = false)
    var postId: String = UUID.randomUUID().toString(),
    @Column(name = "title")
    var title: String = "",
    @Column(name = "description", nullable = true)
    var description: String? = null,

    @Column(name = "created_date")
    var createdDate: Date = Date(System.currentTimeMillis()),

    @ManyToOne
    @JoinColumn(name = "profile_id")
    val profile: Profile = Profile(),

    @OneToOne
    @JoinColumn(name = "rating_id")
    var rating: Rating = Rating(),

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category = Category(),
)

