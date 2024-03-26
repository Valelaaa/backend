package com.maib.backend.entity.profile

import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.rating.Rating
import com.maib.backend.entity.user.User
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "profiles")
data class Profile(
        @Id
        @Column(name = "profile_id")
        var profileId: String = UUID.randomUUID().toString(),
        @Column(name = "user_picture")

        val userPicture: String? = null,
        @OneToOne
        @JoinColumn(name = "user_id")
        var user: User = User(),
        @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL])
        val posts: MutableList<Post> = mutableListOf(),
        @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL])
        val comments: MutableList<Comment> = mutableListOf(),

        @ManyToMany
        @JoinTable(
                name = "profiles_ratings",
                joinColumns = [JoinColumn(name = "profile_id")],
                inverseJoinColumns = [JoinColumn(name = "rating_id")]
        )
        val ratings: MutableList<Rating> = mutableListOf()
)