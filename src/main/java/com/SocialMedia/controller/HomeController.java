package com.SocialMedia.controller;

import com.SocialMedia.entity.User;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
}
