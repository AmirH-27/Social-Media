package com.SocialMedia.service;

import com.SocialMedia.entity.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> findPaginated(int userId, int pageNo, int pageSize);
}
