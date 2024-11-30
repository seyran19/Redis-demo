package com.example.redisdemo.controller;

import com.example.redisdemo.entity.User;
import com.example.redisdemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Patch;

@RestController
@RequestMapping("/api")
public class MainController {
    
    private final UserService userService;


    public MainController(UserService userService) {
        this.userService = userService;
    }
 /// https://www.youtube.com/watch?v=Ob0CvLKvqpA

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<User> get(@RequestParam int id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<User> update(@RequestParam Integer id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserById(id, user), HttpStatus.OK);
    }
}
