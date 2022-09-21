package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.CommentRepo;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @PostMapping("/signup")
    public User signup(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("gender") char gender, @RequestParam("profilePicture") String profilePicture) {
        User user = new User(name, email, password, gender, profilePicture);
        userRepo.save(user);
        return user;
    }

    @PostMapping("/login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        return user;
    }
    @PostMapping("/updateProfile")
    public User update(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("gender") char gender, @RequestParam("profilePicture") MultipartFile profilePicture) {
        User user = userRepo.findByEmailAndPassword(email, password);
        user.setFullName(name);
        user.setGender(gender);
        user.setProfilePicture(profilePicture.getOriginalFilename());
        userRepo.save(user);
        return user;
    }

    @PostMapping("/updatePost")
    public Post uploadPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile, @RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Post post = postRepo.findById(postId).get();
        User user = userRepo.findById(userId).get();
        post = new Post(caption, postFile.getOriginalFilename(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), user);
        postRepo.save(post);
        return post;
    }

    @PostMapping("/deletePost")
    public String deletePost(@RequestParam("postId") int postId) {
        postRepo.deleteById(postId);
        return "Post deleted";
    }

    @PostMapping("/user/add/post")
    public Post userPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile, @RequestParam("userId") int userId) {
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = LocalDateTime.now().toString();
        Post post = new Post(caption, postFile.getOriginalFilename(), createdAt, updatedAt, userRepo.findById(userId).get());
        postRepo.save(post);
        return post;
    }

    @GetMapping("/home")
    public List<Post> home() {
        List<Post>allPosts = postRepo.findAll();
        return allPosts;
    }

}
