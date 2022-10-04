package com.SocialMedia.repo;

import com.SocialMedia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPost(Post post);
}

