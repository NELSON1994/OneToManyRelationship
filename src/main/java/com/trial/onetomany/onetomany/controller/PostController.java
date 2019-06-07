package com.trial.onetomany.onetomany.controller;

import com.trial.onetomany.onetomany.exception.ResourceNotFoundException;
import com.trial.onetomany.onetomany.model.Post;
import com.trial.onetomany.onetomany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
// base url
@RequestMapping("manytoone/nel")
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/posts")
    public List<Post> getAllPosts(){

        return postRepository.findAll();
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post){
        return postRepository.save(post);

    }

    @PutMapping("/posts/{postId}")
    public  Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest){
        return postRepository.findById(postId).map(post ->{
           post.setTitle(postRequest.getTitle());
           post.setDescription(postRequest.getDescription());
           post.setContent(postRequest.getContent());
           return postRepository.save(post);

        }).orElseThrow( ()->new ResourceNotFoundException("PostId" + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() ->new ResourceNotFoundException("PostId" + postId + "not found"));
    }
}
