package com.milak.service;

import com.milak.model.Post;
import com.milak.model.User;
import com.milak.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> getAllPosts() {
        return postRepository.getAllPosts().stream().peek(post -> {
            if (post.getPicture() != null) {
                post.setPicture(convertPicture(post.getPicture(), post.getId()));
            }
        }).collect(Collectors.toList());
    }

    public byte[] convertPicture(byte[] original, int postId) {
        if (postId % 2 == 0) {
            return blurImage(original);
        }
        return grayScaleImage(original);
    }

    public byte[] grayScaleImage(byte[] original) {
        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(original));
            int width = bi.getWidth();
            int height = bi.getHeight();

            BufferedImage result = new BufferedImage(
                    width,
                    height,
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(bi, 0, 0, Color.WHITE, null);

            for (int i = 0; i < result.getHeight(); i++) {
                for (int j = 0; j < result.getWidth(); j++) {
                    Color c = new Color(result.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(
                            red + green + blue,
                            red + green + blue,
                            red + green + blue);
                    result.setRGB(j, i, newColor.getRGB());
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(result, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

    public byte[] blurImage(byte[] original) {
        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(original));
            Kernel kernel = new Kernel(3, 3, new float[] {
                    1f / 9f, 1f / 9f, 1f / 9f,
                    1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f
            });
            BufferedImageOp biop = new ConvolveOp(kernel);
            BufferedImage newBi = biop.filter(bi, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newBi, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
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

    public void saveImage(byte[] bytes, int postId, String username) {
        postRepository.saveImage(bytes, postId);
        try {
            User user = userService.findUserByUsername(username);
            userService.updateUserLimit(user.getUuid());
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
