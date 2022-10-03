package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiPostRes;
import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.ReactionType;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.*;
import com.SocialMedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReactionRepo reactionRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostService postService;


    @PostMapping("/user/add")
    public Post userPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                         @RequestParam("userId") int userId) {
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = LocalDateTime.now().toString();
        Post post = new Post(caption, postFile.getOriginalFilename(), createdAt, updatedAt, userRepo.findById(userId).get());
        postRepo.save(post);
        return post;
    }

    @PostMapping("/update/post")
    public Post uploadPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                           @RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Post post = postRepo.findById(postId).get();
        User user = userRepo.findById(userId).get();
        postRepo.delete(post);
        post = new Post(caption, postFile.getOriginalFilename(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), user);
        postRepo.save(post);
        return post;
    }

    @PostMapping("/delete/post")
    public String deletePost(@RequestParam("postId") int postId) {
        postRepo.deleteById(postId);
        return "Post deleted";
    }

    @GetMapping("/all/{pageNo}/{pageSize}")
    public List<ApiPostRes> getPostByApiRes(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        List<Post> posts = postService.findPaginated(pageNo, pageSize);
        List<ApiPostRes> apiPostResList = new ArrayList<>();
        for(Post post : posts){
            apiPostResList.add(new ApiPostRes(reactionRepo.countAllByPostIdAndReactionType(post.getId(), ReactionType.LIKE),
                    reactionRepo.countAllByPostIdAndReactionType(post.getId(), ReactionType.DISLIKE),
                    postRepo.findById(post.getId()).get(), commentRepo.findAllByPost(postRepo.findById(post.getId()).get())));
        }
        return apiPostResList;
    }

    @GetMapping("/{id}")
    public ApiPostRes getPostById(@PathVariable("id") int id) {
        return new ApiPostRes(reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.LIKE),
                reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.DISLIKE),
                postRepo.findById(id).get(), commentRepo.findAllByPost(postRepo.findById(id).get()));
    }
}
