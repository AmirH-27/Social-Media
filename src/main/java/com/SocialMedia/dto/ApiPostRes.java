package com.SocialMedia.dto;

import com.SocialMedia.entity.Comment;
import com.SocialMedia.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;

import java.awt.print.Pageable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPostRes {
    int numberOfPost;
    int likes;
    int dislikes;
    Post post;
    List<Comment> comment;
    int pageNumber;

    PageRequest pageable;

    public ApiPostRes(int likes, int dislikes, Post post, List<Comment> comment) {
        this.likes = likes;
        this.dislikes = dislikes;
        this.post = post;
        this.comment = comment;
    }
}
