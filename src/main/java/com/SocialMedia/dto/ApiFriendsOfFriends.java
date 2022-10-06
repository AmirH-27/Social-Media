package com.SocialMedia.dto;

import com.SocialMedia.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApiFriendsOfFriends {
    User friend;
    User friendOfFriend;
    int pageNumber;
}
