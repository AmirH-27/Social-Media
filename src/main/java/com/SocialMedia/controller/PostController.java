package com.SocialMedia.controller;

import com.SocialMedia.entity.Comment;
import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ReactionRepo reactionRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user/add")
    public Post userPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                         @RequestParam("userId") int userId) {
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = LocalDateTime.now().toString();
        Post post = new Post(caption, postFile.getOriginalFilename(), createdAt, updatedAt, userRepo.findById(userId).get());
        postRepo.save(post);
        return post;
    }

    @PostMapping("/update/post")
    public Post uploadPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                           @RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Post post = postRepo.findById(postId).get();
        User user = userRepo.findById(userId).get();
        postRepo.delete(post);
        post = new Post(caption, postFile.getOriginalFilename(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), user);
        postRepo.save(post);
        return post;
    }

    @PostMapping("/delete/post")
    public String deletePost(@RequestParam("postId") int postId) {
        postRepo.deleteById(postId);
        return "Post deleted";
    }

    @GetMapping("")
    public List<Post> getPost(@RequestParam("userId") int userId) {
        return postRepo.findByUserId(userRepo.findById(userId).get().getId());
    }
}
