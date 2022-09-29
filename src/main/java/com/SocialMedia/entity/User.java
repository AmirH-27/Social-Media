package com.SocialMedia.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String password;
    private char gender;
    private String profilePicture;

    public User(String fullName, String email, String password, char gender, String profilePicture) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.profilePicture = profilePicture;
    }
}
