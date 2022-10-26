package com.example.springdemoapi.payload;

import lombok.Data;

@Data
public class UserRegisterPayload {

    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
