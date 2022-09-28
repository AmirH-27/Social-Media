package com.SocialMedia.controller;

import com.SocialMedia.entity.Comment;
import com.SocialMedia.repo.CommentRepo;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.ReactionRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ReactionRepo reactionRepo;
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/add")
    public Comment comment(@RequestParam("comment") String text, @RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Comment comment = new Comment(text, userRepo.findById(userId).get(), postRepo.findById(postId).get());
        commentRepo.save(comment);
        return comment;
    }
    @PostMapping("/delete")
    public String deleteComment(@RequestParam("comment") Comment comment) {
        commentRepo.deleteById(commentRepo.findById(comment.getId()).get().getId());
        return "Comment deleted";
    }
    @PostMapping("/update")
    public Comment updateComment(@RequestParam("comment") Comment comment, @RequestParam("text") String text) {
        comment.setComment(text);
        commentRepo.save(comment);
        return comment;
    }
    @GetMapping("/post")
    public List<Comment> getPostComments(@RequestParam("postId") int postId) {
        return commentRepo.findAllByPost(postRepo.findById(postId).get());
    }
}
