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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reaction")
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
        Reaction reaction = reactionRepo.findByUserAndPost(userRepo.findById(userId).get(), postRepo.findById(postId).get());
        if (reaction != null) {
            if(reaction.getReactionType() == ReactionType.DISLIKE) {
                reactionRepo.deleteById(reaction.getId());
                reaction = new Reaction(userRepo.findById(userId).get(), postRepo.findById(postId).get(), ReactionType.LIKE);
                reactionRepo.save(reaction);
                return reaction;
            }
            reactionRepo.deleteById(reaction.getId());
            return null;
        }
        else {
            reaction = new Reaction(userRepo.findById(userId).get(), postRepo.findById(postId).get(), ReactionType.LIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
    }

    @PostMapping("/dislike/post")
    public Reaction dislike(@RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Reaction reaction = reactionRepo.findByUserAndPost(userRepo.findById(userId).get(), postRepo.findById(postId).get());
        if (reaction != null) {
            if(reaction.getReactionType() == ReactionType.LIKE) {
                reactionRepo.deleteById(reaction.getId());
                reaction = new Reaction(userRepo.findById(userId).get(), postRepo.findById(postId).get(), ReactionType.DISLIKE);
                reactionRepo.save(reaction);
                return reaction;
            }
            reactionRepo.deleteById(reaction.getId());
            return null;
        }
        else {
            reaction = new Reaction(userRepo.findById(userId).get(), postRepo.findById(postId).get(), ReactionType.DISLIKE);
            reactionRepo.save(reaction);
            return reaction;
        }
    }

    @GetMapping("/post")
    public List<Reaction> getPostReactions(@RequestParam("postId") int postId) {
        return reactionRepo.findAllByPost(postRepo.findById(postId).get());
    }

}
