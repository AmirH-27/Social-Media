package com.SocialMedia.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isAccepted;

    @ManyToOne
    private User user;

    @ManyToOne
    private User friend;

    public Friend(boolean isAccepted, User user, User friend) {
        this.isAccepted = isAccepted;
        this.user = user;
        this.friend = friend;
    }
}
