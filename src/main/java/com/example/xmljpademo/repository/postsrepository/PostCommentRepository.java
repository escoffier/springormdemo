package com.example.xmljpademo.repository.postsrepository;

import com.example.xmljpademo.model.post.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
