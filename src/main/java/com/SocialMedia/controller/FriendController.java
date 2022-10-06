package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiFriendRequestRes;
import com.SocialMedia.dto.ApiFriendRes;
import com.SocialMedia.entity.Friend;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
import com.SocialMedia.service.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendRepo friendRepo;
    private final UserRepo userRepo;
    private final FriendService friendService;
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
        Page<Friend> friends = friendService.findPaginated(userId, pageNo, pageSize);
        List<ApiFriendRes> apiFriendRes = new ArrayList<>();
        for (Friend friend : friends) {
            apiFriendRes.add(new ApiFriendRes(friends.getNumberOfElements(), friend, PageRequest.of(pageNo, pageSize), friends.getTotalPages()));
        }
        return apiFriendRes;
    }

    @GetMapping("/requests")
    public List<ApiFriendRequestRes> getFriendRequests(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Friend> friends = friendRepo.findAllByUserAndIsAcceptedFalse(userId, paging);
        return friends.map(friend -> new ApiFriendRequestRes(friends.getNumberOfElements(), friends.toList(), PageRequest.of(pageNo, pageSize), friends.getTotalPages())).getContent();
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
        //todo
        return null;
    }
}
