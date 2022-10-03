package com.SocialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPostRes<T> {
    int likes;
    int dislikes;
    T post;
    T comment;
}
