package com.SocialMedia.service;

import com.SocialMedia.entity.Friend;
import com.SocialMedia.entity.Post;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImp implements FriendService{

    private FriendRepo friendRepo;
    private UserRepo userRepo;

    public FriendServiceImp(FriendRepo friendRepo, UserRepo userRepo) {
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
    }
    @Override
    public Page<User> findPaginated(int userId, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<User> friends = userRepo.findAllById(friendRepo.findFriend(userId, paging));
        Page<User> users = new PageImpl<>(friends, paging, friends.size());
        return users;
    }

    @Override
    public Page<Friend> findPaginatedByUserAndAcceptedFalse(int pageNo, int pageSize, int userId) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Friend> friends = friendRepo.findAllByUserAndIsAcceptedFalse(userId, paging);
        return friends;
    }

    @Override
    public Page<User> findPaginatedFoF(int userId, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<User> friends = userRepo.findAllById(friendRepo.findFriend(userId, paging));
        Page<User> users = new PageImpl<>(friends, paging, friends.size());
        return users;
    }

}
