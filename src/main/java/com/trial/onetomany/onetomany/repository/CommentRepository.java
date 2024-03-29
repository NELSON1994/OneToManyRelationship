package com.trial.onetomany.onetomany.repository;

import com.trial.onetomany.onetomany.model.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostId(Long postId);
    Optional<Comments> findByIdAndPostId(Long id,Long postId);

}
