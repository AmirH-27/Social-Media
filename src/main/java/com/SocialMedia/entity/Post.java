package com.SocialMedia.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String caption;
    private String postFile;
    private String createdAt;
    private String updatedAt;

    @ManyToOne
    private User user;

    public Post(String caption, String postFile, String createdAt, String updatedAt, User user) {
        this.caption = caption;
        this.postFile = postFile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Post(String caption, String createdAt, String updatedAt, User user) {
        this.caption = caption;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}
