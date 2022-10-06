package com.SocialMedia.dto;

import com.SocialMedia.entity.Comment;
import com.SocialMedia.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPostRes {
    int numberOfPosts;
    int likes;
    int dislikes;
    Post post;
    List<Comment> comment;
    int pageNumber;

    Pageable pageable;

    int total_pages;

    public ApiPostRes(int likes, int dislikes, Post post, List<Comment> comment) {
        this.likes = likes;
        this.dislikes = dislikes;
        this.post = post;
        this.comment = comment;
    }
}
