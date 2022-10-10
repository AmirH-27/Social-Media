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

    Friend findByFriendId(int user);

    @Query(value="SELECT * FROM friend WHERE friend_id = ?1 AND is_accepted = false", nativeQuery = true)
    Page<Friend> findAllByUserAndIsAcceptedFalse(int userId, Pageable paging);
    @Query(value="SELECT * FROM friend WHERE (user_id = ?1 OR friend_id = ?1) AND is_accepted = true",
            nativeQuery = true)
    Page<Friend> findAllByUser(Integer userId, Pageable pageable);

    @Query(value="SELECT * FROM friend WHERE (user_id = ?1 AND friend_id = ?2) OR (user_id = ?2 AND friend_id = ?1) AND is_accepted = true",
            nativeQuery = true)
    Friend findFriend(Integer userId, Integer friendId);

    @Query(value="SELECT friend_id FROM friend WHERE user_id = ?1 AND is_accepted = true", nativeQuery = true)
    List<Integer> findFriendIds(Integer userId);

    @Query(value="SELECT user_id FROM friend WHERE friend_id = ?1 AND is_accepted = true\n" +
            "union all\n" +
            "SELECT friend_id FROM friend WHERE user_id = ?1 AND is_accepted = true", nativeQuery = true)
    Page<Integer> findFriend(Integer userId, Pageable pageable);
}
