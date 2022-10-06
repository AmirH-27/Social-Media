package com.SocialMedia.service;

import com.SocialMedia.entity.Post;


import java.util.List;

public interface PostService {
    List<Post> findPaginated(int pageNo, int pageSize);
    List<Post> findPaginatedByUser(int pageNo, int pageSize, int userId);
}
