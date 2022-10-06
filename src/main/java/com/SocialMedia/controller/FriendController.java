package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiFriendRes;
import com.SocialMedia.dto.ApiFriendsOfFriends;
import com.SocialMedia.entity.Friend;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
import com.SocialMedia.service.FriendService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private FriendRepo friendRepo;
    private UserRepo userRepo;
    private FriendService friendService;
    public FriendController(FriendRepo friendRepo, UserRepo userRepo, FriendService friendService) {
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public Friend addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) {
        if (friendRepo.findFriend(userId, friendId)==null) {
            return friendRepo.save(new Friend(false, userRepo.findById(userId).get(), userRepo.findById(friendId).get()));
        }
        return null;
    }

    @GetMapping("/list")
    public List<ApiFriendRes> getFriends(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        List<Friend> friends = friendService.findPaginated(userId, pageNo, pageSize);
        List<ApiFriendRes> apiFriendRes = new ArrayList<>();
        int count = 0;
        for (Friend friend : friends) {
            apiFriendRes.add(new ApiFriendRes(count++, friend, PageRequest.of(pageNo, pageSize)));
        }
        return apiFriendRes;
    }

    @GetMapping("/requests")
    public List<Friend> getFriendRequests(@RequestParam("userId") int userId) {
        List<Friend> requests = new ArrayList<>();
        for (Friend friend : friendRepo.findAllByUser(userRepo.findById(userId).get())) {
            if (!friend.isAccepted()) {
                requests.add(friend);
            }
        }
        return requests;
    }

    @PostMapping("/accept")
    public Friend acceptFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) {
        Friend friend = friendRepo.findByUserAndFriend(userRepo.findById(friendId).get(), userRepo.findById(userId).get());
        friend.setAccepted(true);
        friendRepo.save(friend);
        return friend;
    }

    @PostMapping("/delete")
    public Friend deleteFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) {
        Friend friend = friendRepo.findByUserAndFriend(userRepo.findById(friendId).get(), userRepo.findById(userId).get());
        friendRepo.delete(friend);
        return friend;
    }
    @GetMapping("/friendsOfFriends")
    public List<Friend> getFriendsOfFriends(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return friendRepo.findFriendIds(userId);
    }
}
