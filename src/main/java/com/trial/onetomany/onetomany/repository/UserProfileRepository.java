package com.trial.onetomany.onetomany.repository;

import com.trial.onetomany.onetomany.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository  extends JpaRepository<UserProfile, Long> {

    List<UserProfile> findByUserId(Long userId);

    Optional<Object> findByIdAndUserId(Long userprofileId, Long userId);

    void delete(UserProfile userprofile);
}
