package com.task.jwt.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AuthenticationRequest{
    private String email;
    private String password;
}
