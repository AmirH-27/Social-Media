package com.SocialMedia.service;

import com.SocialMedia.entity.Post;
import org.springframework.data.domain.Page;


import java.util.List;

public interface PostService {
    Page<Post> findPaginated(int pageNo, int pageSize);
    Page<Post> findPaginatedByUser(int pageNo, int pageSize, int userId);
}
