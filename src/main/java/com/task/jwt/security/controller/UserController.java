package com.task.jwt.security.controller;
import com.task.jwt.security.service.GoRestWebclientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.GuardedObject;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GoRestWebclientService goRestWebclientService;

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        String response = goRestWebclientService.getData("/v2/users").toString();
        return ResponseEntity.ok(response);
    }
}
