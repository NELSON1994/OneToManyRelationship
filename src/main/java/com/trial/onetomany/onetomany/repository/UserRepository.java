package com.trial.onetomany.onetomany.repository;

import com.trial.onetomany.onetomany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

}
