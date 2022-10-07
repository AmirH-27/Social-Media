package com.SocialMedia.service;

import com.SocialMedia.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FriendService {
    Page<User> findPaginated(int userId, int pageNo, int pageSize);
    Page<Friend> findPaginatedByUserAndAcceptedFalse(int pageNo, int pageSize, int userId);

    Page<User> findPaginatedFoF(int userId, int pageNo, int pageSize);

}
