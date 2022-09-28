package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.CommentRepo;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/home")
    public List<Post> home() {
        List<Post>allPosts = postRepo.findAll();
        return allPosts;
    }

    @PostMapping("/signup")
    public User signup(@RequestParam("name") String name, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("gender") char gender,
                       @RequestParam("profilePicture") MultipartFile profilePicture) {
        User user = new User(name, email, password, gender, profilePicture.getOriginalFilename());
        userRepo.save(user);
        return user;
    }

    @PostMapping("/signin")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        return user;
    }

    @PostMapping("/update/profile")
    public User update(@RequestParam("name") String name, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("gender") char gender,
                       @RequestParam("profilePicture") MultipartFile profilePicture) {
        User user = userRepo.findByEmailAndPassword(email, password);
        user.setFullName(name);
        user.setGender(gender);
        user.setProfilePicture(profilePicture.getOriginalFilename());
        userRepo.save(user);
        return user;
    }

}
