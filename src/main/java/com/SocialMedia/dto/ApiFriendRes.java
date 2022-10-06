package com.SocialMedia.dto;

import com.SocialMedia.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiFriendRes {
    int numberOfFriend;
    Friend friend;
    PageRequest pageable;
}
