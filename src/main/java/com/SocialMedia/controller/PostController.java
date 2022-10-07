package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiPostRes;
import com.SocialMedia.entity.*;
import com.SocialMedia.repo.*;
import com.SocialMedia.service.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final ReactionRepo reactionRepo;
    private final CommentRepo commentRepo;
    private final PostService postService;
    public PostController(PostRepo postRepo, UserRepo userRepo, ReactionRepo reactionRepo, CommentRepo commentRepo, PostService postService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.reactionRepo = reactionRepo;
        this.commentRepo = commentRepo;
        this.postService = postService;
    }
    //get post with pagination
    public List<ApiPostRes> getApiPostRes(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @NotNull Page<Post> posts) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return posts.map(post -> new ApiPostRes(posts.getNumberOfElements(), reactionRepo.countAllByPostIdAndReactionType(post.getId(), ReactionType.LIKE),
                reactionRepo.countAllByPostIdAndReactionType(post.getId(), ReactionType.DISLIKE),
                postRepo.findById(post.getId()).get(), commentRepo.findAllByPost(postRepo.findById(post.getId()).get()),
                pageNo+1, paging, posts.getTotalPages())
        ).getContent();
    }

    @PostMapping("/user/add")
    public Post userPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                         @RequestParam("userId") int userId) {
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = LocalDateTime.now().toString();
        Post post = new Post(caption, postFile.getOriginalFilename(), createdAt,
                updatedAt, userRepo.findById(userId).get());
        postRepo.save(post);
        return post;
    }

    @PostMapping("/update/post")
    public Post uploadPost(@RequestParam("caption") String caption, @RequestParam("postFile") MultipartFile postFile,
                           @RequestParam("userId") int userId, @RequestParam("postId") int postId) {
        Post post = postRepo.findById(postId).get();
        User user = userRepo.findById(userId).get();
        postRepo.delete(post);
        post = new Post(caption, postFile.getOriginalFilename(), LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), user);
        postRepo.save(post);
        return post;
    }

    @PostMapping("/delete/post")
    public String deletePost(@RequestParam("postId") int postId) {
        postRepo.deleteById(postId);
        return "Post deleted";
    }
    //get all post
    @GetMapping("/all")
    public List<ApiPostRes> getPostByApiRes(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<Post> posts = postService.findPaginated(pageNo, pageSize);
        return getApiPostRes(pageNo, pageSize, posts);
    }
    //get post by post id
    @GetMapping("/{id}")
    public ApiPostRes getPostById(@PathVariable("id") int id) {
        return new ApiPostRes(reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.LIKE),
                reactionRepo.countAllByPostIdAndReactionType(id, ReactionType.DISLIKE),
                postRepo.findById(id).get(), commentRepo.findAllByPost(postRepo.findById(id).get())
        );
    }
    //get post by user with pagination
    @GetMapping("/user")
    public List<ApiPostRes> getPostByUser(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("userId") int userId) {
        Page<Post> posts = postService.findPaginatedByUser(pageNo, pageSize, userId);
        return getApiPostRes(pageNo, pageSize, posts);
    }

}
