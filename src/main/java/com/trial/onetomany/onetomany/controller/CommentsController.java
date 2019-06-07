package com.trial.onetomany.onetomany.controller;

import com.trial.onetomany.onetomany.exception.ResourceNotFoundException;
import com.trial.onetomany.onetomany.model.Comments;
import com.trial.onetomany.onetomany.repository.CommentRepository;
import com.trial.onetomany.onetomany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
// base url
@RequestMapping("manytoone/nel")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
// retrieving all comments according to the post id
    @GetMapping("/posts/{postId}/comments")
    public List<Comments> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentRepository.findByPostId(postId);
    }


//    creating comment according to the post id
    @PostMapping("/posts/{postId}/comments")
    public Comments createComment(@PathVariable(value = "postId") Long postId, @RequestBody Comments comment){
        return commentRepository.save(comment);
    }

//    updating comment according to post id and comment id
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comments updateComment(@PathVariable(value = "postId") Long postId,
                                  @PathVariable(value = "commentId")Long commentId,
                                  @Valid @RequestBody Comments commentRequest){
        if(!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Post with the Id" + postId + "not found");
    }

        return commentRepository.findById(commentId).map(comments -> {
            comments.setText(commentRequest.getText());
            return commentRepository.save(comments);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment with the id " + commentId + "not found"));

    }

// deleting comment
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId,
                             @PathVariable(value = "commentId") Long commentId){
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment with the id" + commentId + "not found"));
    }

}
