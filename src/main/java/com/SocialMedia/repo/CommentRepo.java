package com.SocialMedia.repo;

import com.SocialMedia.entity.Comment;
import com.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPost(Post post);

}

