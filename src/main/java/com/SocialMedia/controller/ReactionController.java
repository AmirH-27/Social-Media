package com.SocialMedia.controller;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.Reaction;
import com.SocialMedia.entity.ReactionType;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.CommentRepo;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.ReactionRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReactionController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ReactionRepo reactionRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/like/post")
    public Reaction likePost(@RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        User user = userRepo.findById(userId).get();
        Post post = postRepo.findById(postId).get();
        Reaction reaction;
        if(reactionRepo.findByUserId(userId)==null && reactionRepo.findByPostId(postId)==null){
            reaction = new Reaction(user, post, ReactionType.LIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
        else if(reactionRepo.findByUserId(userId).getReactionType() == ReactionType.LIKE && reactionRepo.findByPostId(postId).getId() == postId){
            reactionRepo.delete(reactionRepo.findByUserId(userId));
            return null;
        }
        else if(reactionRepo.findByUserId(userId).getReactionType() == ReactionType.DISLIKE){
            reaction = reactionRepo.findByUserId(userId);
            reaction.setReactionType(ReactionType.LIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
        else{
            return null;
        }
    }

    @PostMapping("/dislike/post")
    public Reaction dislike(@RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        User user = userRepo.findById(userId).get();
        Post post = postRepo.findById(postId).get();
        Reaction reaction;
        if(reactionRepo.findByUserId(userId)==null && reactionRepo.findByPostId(postId)==null){
            reaction = new Reaction(user, post, ReactionType.DISLIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
        else if(reactionRepo.findByUserId(userId).getReactionType() == ReactionType.DISLIKE && reactionRepo.findByPostId(postId).getId() == postId) {
            reactionRepo.delete(reactionRepo.findByUserId(userId));
            return null;
        }
        else if(reactionRepo.findByUserId(userId).getReactionType() == ReactionType.LIKE){
            reaction = reactionRepo.findByUserId(userId);
            reaction.setReactionType(ReactionType.DISLIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
        else{
            return null;
        }
    }

    @GetMapping("/post/reactions")
    public List<Reaction> getPostReactions(@RequestParam("postId") int postId) {
        return reactionRepo.findAllByPost(postRepo.findById(postId).get());
    }

}
