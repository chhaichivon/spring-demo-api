package com.example.springdemoapi.service;

import com.example.springdemoapi.model.UserEntity;
import com.example.springdemoapi.payload.UserRegisterPayload;
import com.example.springdemoapi.payload.UserUpdatePayload;
import com.example.springdemoapi.repository.RoleRepository;
import com.example.springdemoapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> findAll(Integer page, Integer size){
        return userRepository.findAll();
    }

    public UserEntity findById(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity save(UserRegisterPayload userRegisterPayload){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRegisterPayload.getEmail());
        userEntity.setPassword(userRegisterPayload.getPassword());
        return userRepository.save(userEntity);
    }

    public UserEntity update(Integer userId, UserUpdatePayload userUpdatePayload){
        UserEntity userEntity = findById(userId);
        return userRepository.save(userEntity);
    }

    public UserEntity delete(Integer userId){
        UserEntity userEntity = findById(userId);
        if(Objects.nonNull(userEntity))
            userRepository.deleteById(userId);
        return userEntity;
    }
}
