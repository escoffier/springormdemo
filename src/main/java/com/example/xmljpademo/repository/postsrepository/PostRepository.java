package com.example.xmljpademo.repository.postsrepository;

import com.example.xmljpademo.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
