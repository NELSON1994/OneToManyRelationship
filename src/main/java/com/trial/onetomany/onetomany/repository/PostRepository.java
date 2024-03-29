package com.trial.onetomany.onetomany.repository;

import com.trial.onetomany.onetomany.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
        List<Post> findAll();


//    Page<Post> findAll(Pageable pageable);
}
