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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Override
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepo.findAll(paging);
        return posts;
    }

    @Override
    public Page<Post> findPaginatedByUser(int pageNo, int pageSize, int userId, String sortField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepo.findAllByUserId(userId, paging);
        return posts;
    }

}
