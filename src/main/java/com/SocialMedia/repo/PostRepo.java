package com.SocialMedia.repo;

import com.SocialMedia.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepo extends JpaRepository<Post, Integer> {
    Page<Post> findAllByUserId(int userId, Pageable paging);
}
