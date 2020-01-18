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
        return postRepository.getAllPosts();
    }

    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }

    public void insertPost(Post post, String username) {
        try {
            post.setUser(userService.findUserByUsername(username));
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return;
        }
        postRepository.insertPost(post);
    }

    public void deletePost(int postId) {
        postRepository.deletePost(postId);
    }
}
