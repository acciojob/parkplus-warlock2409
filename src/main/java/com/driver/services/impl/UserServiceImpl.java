package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import com.driver.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository4;
    @Override
    public void deleteUser(Integer userId) {
        Optional<User> user = userRepository4.findById(userId);

        User deleteUser = user.get();

        userRepository4.deleteById(userId);



    }

    @Override
    public User updatePassword(Integer userId, String password) {
        Optional<User> oldUser = userRepository4.findById(userId);

        User user = oldUser.get();

        user.setPassword(password);

       User responseUser = userRepository4.save(user);

       User updatedUser = UserTransformer.userToResponseDto(responseUser);


        return updatedUser;
    }

    @Override
    public void register(String name, String phoneNumber, String password) {
        User newUser = UserTransformer.buildUser(name, phoneNumber, password);
        User response = userRepository4.save(newUser);
    }
}
