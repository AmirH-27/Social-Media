package com.SocialMedia.repo;

import com.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int id);
}
