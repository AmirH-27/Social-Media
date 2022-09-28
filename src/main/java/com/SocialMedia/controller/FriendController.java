package com.SocialMedia.controller;

import com.SocialMedia.entity.Friend;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
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

    @PostMapping("/add")
    public Friend addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) {
        if (userRepo.existsById(userId) && userRepo.existsById(friendId)) {
            return friendRepo.save(new Friend(false, userRepo.findById(userId).get(), userRepo.findById(friendId).get()));
        }
        return null;
    }

    @GetMapping("/list")
    public List<Friend> getFriends(@RequestParam("userId") int userId) {
        return friendRepo.findAllByUser(userRepo.findById(userId).get());
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
    public Friend acceptFriend(@RequestParam("friendId") int friendId, @RequestParam("userId") int userId) {
        Friend friend = friendRepo.findByUserAndFriend(userRepo.findById(userId).get(), userRepo.findById(friendId).get());
        friend.setAccepted(true);
        friendRepo.save(friend);
        return friend;
    }
}
