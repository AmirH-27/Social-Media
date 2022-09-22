package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", userRepo.findByEmail(authentication.getName()).getFullName());
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }
}
