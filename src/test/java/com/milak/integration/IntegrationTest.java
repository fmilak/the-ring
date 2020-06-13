package com.milak.integration;

import com.milak.model.Post;
import com.milak.model.Role;
import com.milak.model.User;
import com.milak.service.PostService;
import com.milak.service.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Test
    void userIntegrationTest() throws Exception {
        userService.registerUser(getUser());

        User newUser = userService.findUserByUsername(getUser().getUsername());

        assertSame(newUser.getUsername(), getUser().getUsername());
        assertSame(newUser.getName(), getUser().getName());
        assertSame(newUser.getSurname(), getUser().getSurname());
        assertSame(newUser.getEmail(), getUser().getEmail());

        userService.deleteUser(getUser().getUsername());
    }

    @Test
    void postIntegrationTest() {
        User user = userService.getAllUsers().get(0);
        postService.insertPost(getPost(), user.getUsername());

        List<Post> allPosts = postService.getAllPosts();
        Post lastPost = allPosts.stream().max(Comparator.comparing(Post::getId)).get();

        assertTrue(StringUtils.equals(lastPost.getText(), getPost().getText()));
        assertSame(lastPost.getUser().getUsername(), user.getUsername());

        postService.deletePost(lastPost.getId());
    }

    private User getUser() {
        return new User.Builder()
                .setUuid("uuid")
                .setName("name")
                .setSurname("surname")
                .setUsername("username")
                .setPassword("password")
                .setRole(Role.USER)
                .setMaxLimit(0)
                .setCurrentLimit(0)
                .build();
    }

    private Post getPost() {
        Post post = new Post();
        post.setText("Some Text");
        return post;
    }

}
