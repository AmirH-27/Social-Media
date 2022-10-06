package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public UserController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/home")
    public List<Post> home() {
        return postRepo.findAll();
    }

    @PostMapping("/signup")
    public User signup(@RequestParam("name") String name, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("gender") char gender,
                       @RequestParam("profilePicture") MultipartFile profilePicture) {
        User user = new User(name, email, password, gender, profilePicture.getOriginalFilename());
        userRepo.save(user);
        return user;
    }

    @PostMapping("/sign-in")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userRepo.findByEmailAndPassword(email, password);
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
