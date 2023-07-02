package com.driver.controllers;
import com.driver.model.User;
import com.driver.services.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
//    UserController:
//    Deleting a user: The application needs functionality to delete a user by providing their ID.
    @Autowired
    UserServiceImpl userService;

    // Registering a user: The application needs a functionality to register a new user with their name, phone number, and password.
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String password){
        userService.register(name,phoneNumber,password);
        return new ResponseEntity<>(HttpStatus.OK);
//        try{
//            userService.register(name,phoneNumber,password);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        }

    }

//    Updating user password: The application needs a functionality to update the password of an existing user by providing the user's ID and the new password.
    @PutMapping("/update")
    public ResponseEntity<User> updatePassword(@RequestParam Integer userId, @RequestParam String password) {
        User updatedUser = userService.updatePassword(userId,password);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        try{
//            User updatedUser = userService.updatePassword(userId,password);
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer userId)  {
        userService.deleteUser(userId);

//        try {
//            userService.deleteUser(userId);
//        }catch (Exception e){
//          System.out.println(e.getMessage());
//        }
    }
}
