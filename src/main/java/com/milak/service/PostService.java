package com.milak.service;

import com.milak.model.Post;
import com.milak.model.User;
import com.milak.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PostService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.getAllPosts();
        for (Post post :
                posts) {
            post.setUser(userService.getUserByUuid(post.getUserUuid()));
        }
        return posts;
    }

    public Post getPostById(int id) {
        Post post = postRepository.getPostById(id);
        post.setUser(userService.getUserByUuid(post.getUserUuid()));
        return post;
    }

    public void insertPost(Post post, String username) {
        try {
            post.setUser(userService.findUserByUsername(username));
            post.setUserUuid(post.getUser().getUuid());
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return;
        }
        postRepository.insertPost(post);
    }

}