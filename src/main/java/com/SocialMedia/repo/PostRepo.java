package com.SocialMedia.repo;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int id);

    List<Post> findAllByUser(User user);
}
