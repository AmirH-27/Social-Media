package com.SocialMedia.repo;

import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {

    Reaction findByUserId(int userId);

    List<Reaction> findAllByPost(Post post);
}
