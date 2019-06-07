package com.trial.onetomany.onetomany.controller;

import com.trial.onetomany.onetomany.exception.ResourceNotFoundException;
import com.trial.onetomany.onetomany.model.User;
import com.trial.onetomany.onetomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/onetoone/nel")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);

    }

    @PutMapping("/users/{userId}")
    public  User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest){
        return userRepository.findById(userId).map(post ->{
         User user=new User();
         user.setEmail(userRequest.getEmail());
         user.setFirstName(userRequest.getFirstName());
         user.setFirstName(userRequest.getFirstName());
         user.setUserProfile(userRequest.getUserProfile());
            return userRepository.save(user);

        }).orElseThrow( ()->new ResourceNotFoundException("UserId" + userId + " not found"));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() ->new ResourceNotFoundException("UserId" + userId + "not found"));
    }

}
