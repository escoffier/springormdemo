package com.example.xmljpademo.service;

import com.example.xmljpademo.model.post.Post;
import com.example.xmljpademo.repository.postsrepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(value = "postsTransactionManager")
public class PostsService {

    @Autowired
    private PostRepository postRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }
}
