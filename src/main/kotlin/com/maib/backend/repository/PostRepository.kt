package com.maib.backend.repository

import com.maib.backend.entity.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String> {

}