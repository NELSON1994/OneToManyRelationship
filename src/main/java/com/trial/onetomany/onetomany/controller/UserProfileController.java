package com.trial.onetomany.onetomany.controller;

import com.trial.onetomany.onetomany.exception.ResourceNotFoundException;
import com.trial.onetomany.onetomany.model.UserProfile;
import com.trial.onetomany.onetomany.repository.UserProfileRepository;
import com.trial.onetomany.onetomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserProfileController {


    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

//    retriving all users profile
    @GetMapping("/users/userprofiles")
    public List<UserProfile>getAllUserProfiles(@RequestBody UserProfile userProfile){
        return userProfileRepository.findAll();
    }

// RETRIEVING THE USER PROFILE
    @GetMapping("/users/{userId}/userprofile")
    public List<UserProfile> getUserProfile(@PathVariable(value = "userId") Long userId){
        return userProfileRepository.findByUserId(userId);
    }
//    CREATING USER PROFILE
    @PostMapping("/users/{userId}/userprofile")
    public UserProfile createUserProfile(@Valid @RequestBody UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

//    UPDATING USER PROFILE

    @PutMapping("users/{userId}/userprofile/{userprofileId}")
    public UserProfile updateUserProfile(@PathVariable(value = "userId")Long userId,
                                         @PathVariable(value = "userprofileId") Long userprofileId,
                                         @Valid @RequestBody UserProfile userProfile){
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User with the Id" + userId + "not found");
        }

//        UserProfile userprofile=new UserProfile();
        return userProfileRepository.findById(userprofileId).map(userprofile -> {
            userprofile.setAddress1(userProfile.getAddress1());
            userprofile.setAddress2(userProfile.getAddress2());
            userprofile.setCity(userProfile.getCity());
            userprofile.setCountry(userProfile.getCountry());
            userprofile.setDateOfBirth(userProfile.getDateOfBirth());
            userprofile.setGender(userProfile.getGender());
            userprofile.setPhoneNumber(userProfile.getPhoneNumber());
            userprofile.setState(userProfile.getState());
            userprofile.setZipCode(userProfile.getZipCode());
            userprofile.setStreet(userProfile.getStreet());
            return userProfileRepository.save(userprofile);
        }).orElseThrow(() -> new ResourceNotFoundException("UserProfile with the id " + userprofileId + "not found"));

    }


//    DELETING USER ACCOUNT
    @DeleteMapping("/users/{userId}/userprofile/{userprofileId}")


    public ResponseEntity<?> deleteComment(@PathVariable(value = "userId") Long userId,
                                           @PathVariable(value = "userprofileId") Long userprofileId){
        return userProfileRepository.findByIdAndUserId(userprofileId, userId).map(userprofile -> {
           userProfileRepository.delete((UserProfile) userprofile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment with the id" + userprofileId + "not found"));
    }
}
