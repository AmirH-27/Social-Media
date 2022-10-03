package com.SocialMedia.service;

import com.SocialMedia.dto.ApiPostRes;
import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.ReactionType;
import com.SocialMedia.repo.CommentRepo;
import com.SocialMedia.repo.PostRepo;
import com.SocialMedia.repo.ReactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ReactionRepo reactionRepo;

    @Override
    public List<Post> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepo.findAll(paging);
        return posts.toList();
    }

}
