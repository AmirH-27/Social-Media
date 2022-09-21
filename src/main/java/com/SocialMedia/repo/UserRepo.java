package com.SocialMedia.repo;

import com.SocialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);
}
