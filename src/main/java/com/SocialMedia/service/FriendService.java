package com.SocialMedia.service;

import com.SocialMedia.entity.Friend;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FriendService {
    Page<Friend> findPaginated(int userId, int pageNo, int pageSize);
    Page<Friend> findPaginatedByUserAndAcceptedFalse(int pageNo, int pageSize, int userId);
}
