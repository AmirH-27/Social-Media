package com.SocialMedia.controller;

import com.SocialMedia.entity.*;
import com.SocialMedia.repo.CommentRepo;
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

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private ReactionRepo reactionRepo;
    private CommentRepo commentRepo;

    public ViewController(UserRepo userRepo, PostRepo postRepo, ReactionRepo reactionRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.reactionRepo = reactionRepo;
        this.commentRepo = commentRepo;
    }

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

    @GetMapping("view/post/{id}")
    public String viewPost(Model model, @PathVariable int id){
        model.addAttribute("post", postRepo.findById(id).get());
        int likeCount = reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.LIKE);
        int dislikeCount = reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.DISLIKE);;
        List<Comment> comments = commentRepo.findAllByPost(postRepo.findById(id).get());
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("dislikeCount", dislikeCount);
        model.addAttribute("comments", comments);
        return "viewPost";
    }

    @PostMapping("comment/{id}")
    public String comment(@ModelAttribute("comment") String text, @PathVariable int id){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment(text, userRepo.findByEmail(authentication.getName()), postRepo.findById(id).get());
        commentRepo.save(comment);
        return "redirect:/view/post/" + id;
    }

}
