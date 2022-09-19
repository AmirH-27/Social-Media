package com.SocialMedia.repo;

import com.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
