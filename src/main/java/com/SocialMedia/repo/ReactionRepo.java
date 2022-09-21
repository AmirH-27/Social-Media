package com.SocialMedia.repo;

import com.SocialMedia.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {

}
