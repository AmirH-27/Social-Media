package com.SocialMedia.repo;

import com.SocialMedia.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepo extends JpaRepository<Friend, Integer> {

    List<Friend> findAllByUser(User user);

    Friend findByUserAndFriend(User user, User user1);

    List<Friend> findAllByFriend(User user);

    @Query(value="SELECT * FROM friend WHERE user_id OR friend_id = ?1",
            nativeQuery = true)
    Page<Friend> findAllMutual(Integer userId, Pageable pageable);
}
