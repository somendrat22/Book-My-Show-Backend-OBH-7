package com.acciojobs.book_my_show.controllers;

import com.acciojobs.book_my_show.dtos.UserRequestDto;
import com.acciojobs.book_my_show.models.User;
import com.acciojobs.book_my_show.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/customer/register")
    public ResponseEntity<User> createCustomer(@RequestBody UserRequestDto userRequestDto){
        User user = userService.registerCustomer(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/theater-owner/register")
    public ResponseEntity<User> createTheaterOwner(@RequestBody UserRequestDto userRequestDto){
        // We will be calling userService
        log.info(String.format("Recieved user registration request %s", userRequestDto.toString()));
        User user = userService.registerOwner(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
