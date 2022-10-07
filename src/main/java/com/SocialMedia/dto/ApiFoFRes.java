package com.SocialMedia.dto;
import com.SocialMedia.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiFoFRes {
    User friend;
    int numberOfFriends;
    List<User> friendsOfFriends;
    Pageable pageable;
    int total_pages;
}
