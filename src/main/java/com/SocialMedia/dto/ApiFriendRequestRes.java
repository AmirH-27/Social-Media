package com.SocialMedia.dto;

import com.SocialMedia.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApiFriendRequestRes {
    int numberOfFriends;
    List<Friend> friendReq;
    Pageable pageable;
    int total_pages;
}
