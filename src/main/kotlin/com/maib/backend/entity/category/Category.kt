package com.maib.backend.entity.category

import com.maib.backend.entity.post.Post
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.util.*

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "categories")
data class Category(
    @Id
    @Column(name = "category_id")
    var categoryId: String = UUID.randomUUID().toString(),
    @Column(name = "category_name", unique = true, nullable = false)
    var categoryName: String = "",
    @Column(name = "category_title")
    var categoryTitle: String = "",
    @Column(name = "category_image", nullable = true)
    var categoryImage: String? = null,
    @Column(name = "category_description")
    var categoryDescription: String = "",
    @Column(name = "tag_line")
    var tagLine: String = "",
    @Column(name = "post_count")
    var postCount: Long = 0,

    @OneToMany(mappedBy = "category")
    var posts: List<Post> = emptyList()
)