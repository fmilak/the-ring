package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.Post;
import com.milak.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ApiResponse getAllPosts() {
        ApiResponse apiResponse = new ApiResponse();
        List<Post> posts = postService.getAllPosts();
        apiResponse.setData(posts);
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse getPost(@PathVariable String id) {
        ApiResponse apiResponse = new ApiResponse();
        Post post = postService.getPostById(Integer.parseInt(id));
        apiResponse.setData(post);
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @PostMapping(value = "/insert/{username}")
    public ApiResponse insertPost(@RequestBody Post post, @PathVariable String username) {
        postService.insertPost(post, username);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Post uspješno spremljen");
        return apiResponse;
    }

}
