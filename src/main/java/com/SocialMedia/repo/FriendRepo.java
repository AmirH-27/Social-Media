package com.SocialMedia.repo;

import com.SocialMedia.entity.Friend;
import com.SocialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepo extends JpaRepository<Friend, Integer> {

    List<Friend> findAllByUser(User user);

    Friend findByUserAndFriend(User user, User user1);
}
