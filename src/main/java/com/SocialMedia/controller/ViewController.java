package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.Reaction;
import com.SocialMedia.entity.ReactionType;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.ReactionRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ReactionRepo reactionRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userRepo.findByEmail(authentication.getName()));
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        List<Reaction> reactions = reactionRepo.findAll();
        model.addAttribute("reactions", reactions);
        return "home";
    }

    @PostMapping("/add/post")
    public String userPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = LocalDateTime.now().toString();
        User user = userRepo.findByEmail(authentication.getName());
        Post post = new Post(caption, postFile.getOriginalFilename(), createdAt, updatedAt, user);
        postRepo.save(post);
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String userProfile(Model model, @PathVariable int id){
        model.addAttribute("user", userRepo.findById(id).get());
        return "profile";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@ModelAttribute("fullName") String fullName, @ModelAttribute("gender") char gender, @PathVariable int id) {
        User user = userRepo.findById(id).get();
        user.setFullName(fullName);
        user.setGender(gender);
        userRepo.save(user);
        return "redirect:/";
    }

}
