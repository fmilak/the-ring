package com.milak.repository;

import com.milak.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostRepository {

    void insertPost(Post post);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(int id);

    List<Post> getAllPosts();

}
