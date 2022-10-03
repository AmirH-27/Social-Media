package com.SocialMedia.service;

import com.SocialMedia.dto.ApiPostRes;
import com.SocialMedia.entity.Post;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PostService {
    List<Post> findPaginated(int pageNo, int pageSize);
}
