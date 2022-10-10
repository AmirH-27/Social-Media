package com.SocialMedia.controller;

import com.SocialMedia.dto.ApiFoFRes;
import com.SocialMedia.dto.ApiFriendRequestRes;
import com.SocialMedia.dto.ApiFriendRes;
import com.SocialMedia.entity.Friend;
import com.SocialMedia.entity.User;
import com.SocialMedia.repo.FriendRepo;
import com.SocialMedia.repo.UserRepo;
import com.SocialMedia.service.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
    public ApiFriendRes getFriends(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<User> friendsOfFriends = friendService.findPaginatedFoF(userId, pageNo, pageSize);
       return new ApiFriendRes(userRepo.findById(userId).get(), friendsOfFriends.getNumberOfElements(),
                friendsOfFriends.getContent(), PageRequest.of(pageNo, pageSize), friendsOfFriends.getTotalPages());
    }

    @GetMapping("/requests")
    public List<ApiFriendRequestRes> getFriendRequests(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<Friend> friends = friendService.findPaginatedByUserAndAcceptedFalse(pageNo, pageSize, userId);
        return friends.map(friend -> new ApiFriendRequestRes(friends.getNumberOfElements(), friends.toList(),
                PageRequest.of(pageNo, pageSize), friends.getTotalPages())).getContent();
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
    public List<ApiFoFRes> getFriendsOfFriends(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<User> friendsOfFriends = friendService.findPaginatedFoF(userId, pageNo, pageSize);
        List<ApiFoFRes> apiFoFRes = new ArrayList<>();
        for (User user : friendsOfFriends) {
            Page<User> friends = friendService.findPaginated(user.getId(), pageNo, pageSize);
            apiFoFRes.add(new ApiFoFRes(user, friends.getNumberOfElements(), friends.getContent(),
                    PageRequest.of(pageNo, pageSize), friends.getTotalPages()));
        }
        return apiFoFRes;
    }
}
