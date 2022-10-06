package com.SocialMedia.controller;

import com.SocialMedia.entity.Reaction;
import com.SocialMedia.entity.ReactionType;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.ReactionRepo;
import com.SocialMedia.repo.UserRepo;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
    private final PostRepo postRepo;
    private final ReactionRepo reactionRepo;
    private final UserRepo userRepo;
    public ReactionController(PostRepo postRepo, ReactionRepo reactionRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.reactionRepo = reactionRepo;
        this.userRepo = userRepo;
    }

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
