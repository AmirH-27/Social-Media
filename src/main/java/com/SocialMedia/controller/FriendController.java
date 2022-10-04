package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiFriendRes;
import com.SocialMedia.entity.Friend;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
import com.SocialMedia.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendRepo friendRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public Friend addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) {
        if (userRepo.existsById(userId) && userRepo.existsById(friendId) && userId != friendId &&
                friendRepo.findByUserAndFriend(userRepo.findById(friendId).get(), userRepo.findById(userId).get())==null) {
            return friendRepo.save(new Friend(false, userRepo.findById(userId).get(), userRepo.findById(friendId).get()));
        }
        return null;
    }

    @GetMapping("/list/{pageNo}/{pageSize}")
    public List<ApiFriendRes> getFriends(@RequestParam("userId") int userId, @PathVariable int pageNo, @PathVariable int pageSize) {
        List<Friend> friends = friendService.findPaginated(userId, pageNo, pageSize);
        List<ApiFriendRes> apiFriendRes = new ArrayList<>();
        for (Friend friend : friends) {
            apiFriendRes.add(new ApiFriendRes(friends.size(), friend));
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

    @GetMapping("/isFriend")
    public List<Friend> getFriendsOfFriends(@RequestParam("userId") int userId) {
        List<Friend> friends = friendRepo.findAllByUser(userRepo.findById(userId).get());
        List<Friend> friends1 = friendRepo.findAllByFriend(userRepo.findById(userId).get());
        List<Friend> friendsOfFriend = new ArrayList<>();
        for (Friend friend : friends) {
            if (friend.isAccepted()) {
                friendsOfFriend.add(friend);
            }
        }
        for (Friend friend : friends1) {
            if (friend.isAccepted()) {
                friendsOfFriend.add(friend);
            }
        }
        return friendsOfFriend;
    }
}
