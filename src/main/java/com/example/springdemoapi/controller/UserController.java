package com.example.springdemoapi.controller;

import com.example.springdemoapi.model.ProductEntity;
import com.example.springdemoapi.model.UserEntity;
import com.example.springdemoapi.payload.ProductPayload;
import com.example.springdemoapi.payload.UserRegisterPayload;
import com.example.springdemoapi.payload.UserUpdatePayload;
import com.example.springdemoapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "30") int limit) {
        if(limit > 30){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(userService.findAll(page,limit), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> findById(@PathVariable("productId") Integer userId) {
        if(userId == 0 || userId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> save(@RequestBody UserRegisterPayload userRegisterPayload) {
        if(Objects.nonNull(userRegisterPayload)){
            return new ResponseEntity<>(userService.save(userRegisterPayload), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> update(@PathVariable("userId") Integer userId,
                                                @RequestBody UserUpdatePayload userUpdatePayload) {
        if(userId == 0 || userId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            if(Objects.nonNull(userUpdatePayload)){
                return new ResponseEntity<>(userService.update(userId, userUpdatePayload), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<UserEntity> delete(@PathVariable("userId") Integer userId){
        if(userId == 0 || userId < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(userService.delete(userId), HttpStatus.NOT_FOUND);
        }
    }
}
