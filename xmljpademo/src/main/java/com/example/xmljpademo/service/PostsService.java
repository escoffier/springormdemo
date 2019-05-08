package com.example.xmljpademo.service;

import com.example.xmljpademo.dto.PostCommentDTO;
import com.example.xmljpademo.model.post.Post;
import com.example.xmljpademo.model.post.PostComment;
import com.example.xmljpademo.repository.postsrepository.PostCommentRepository;
import com.example.xmljpademo.repository.postsrepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "postsTransactionManager")
public class PostsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository commentRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }


    //@Transactional
    public PostComment addPostComment(PostCommentDTO comment) {
        Post post = postRepository.getOne(comment.getPostId());
        PostComment postComment = new PostComment();
        postComment.setPost(post);
        postComment.setReview(comment.getReview());
        commentRepository.save(postComment);

        return postComment;
    }
}
