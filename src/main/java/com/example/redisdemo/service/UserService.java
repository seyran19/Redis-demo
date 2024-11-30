package com.example.redisdemo.service;

import com.example.redisdemo.Repository.UserRepository;
import com.example.redisdemo.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    /// value = "users" - наименование кеша в который это будем пихать
    @Cacheable(value = "users", key = "#UserId")
    public User getUserById(int UserId){return userRepository.findById(UserId).orElse(null);

    }

    @Transactional
    public User updateUserById(int UserId, User user){
        User u = getUserById(UserId);
        if (u != null){
            throw new IllegalArgumentException("User not found");
        }
        return userRepository.save(user);
    }

}
