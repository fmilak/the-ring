package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.Post;
import com.milak.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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

    @DeleteMapping("/delete/{postId}")
    public ApiResponse deletePost(@PathVariable String postId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            postService.deletePost(Integer.parseInt(postId));
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            return apiResponse;
        }
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @PostMapping(value = "/upload/{postId}/{username}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse uploadPicture(@RequestParam("image") MultipartFile image, @PathVariable int postId,
                                     @PathVariable String username) {

        ApiResponse apiResponse = new ApiResponse();
        try {
            byte[] bytes = image.getBytes();
            postService.saveImage(bytes, postId, username);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            return apiResponse;
        }
        apiResponse.setSuccess(true);
        return apiResponse;
    }

}
