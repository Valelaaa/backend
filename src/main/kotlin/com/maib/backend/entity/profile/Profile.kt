package com.maib.backend.entity.profile

import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.rating.Rating
import com.maib.backend.entity.user.User
import jakarta.persistence.*
import lombok.Builder
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
    @OneToOne(cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn(name = "user_id")
    var user: User = User(),
    @OneToMany
    var createdPosts:List<Post>? = null,
    @OneToMany
    var createdComments:List<Comment>? = null,

    @ElementCollection
    @CollectionTable(name = "user_ratings", joinColumns = [JoinColumn(name = "profile_id")])
    @MapKeyJoinColumn(name = "rating_id")
    @Column(name = "rating_value")
    var userRatings: Map<Rating, Int>? = null
)