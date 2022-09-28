package com.SocialMedia.repo;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.Reaction;
import com.SocialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {

    Reaction findByUserId(int userId);

    List<Reaction> findAllByPost(Post post);

    Reaction findByPostId(int postId);

    Reaction findByUserAndPost(User user, Post post);
}
