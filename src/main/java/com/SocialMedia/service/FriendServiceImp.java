package com.SocialMedia.service;

import com.SocialMedia.entity.Friend;
import com.SocialMedia.entity.Post;
import com.SocialMedia.repo.FriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImp implements FriendService{

    private FriendRepo friendRepo;

    public FriendServiceImp(FriendRepo friendRepo) {
        this.friendRepo = friendRepo;
    }
    @Override
    public List<Friend> findPaginated(int userId, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Friend> friends = friendRepo.findAllMutual(userId, paging);
        return friends.toList();
    }

}
