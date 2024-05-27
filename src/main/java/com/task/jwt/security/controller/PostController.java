package com.task.jwt.security.controller;

import com.task.jwt.security.service.GoRestWebclientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final String POSTS_API_URL = "/v2/posts";
    private final GoRestWebclientService goRestWebclientService;

    @GetMapping
    public ResponseEntity<String> getAllPosts() {
        String response = goRestWebclientService.getData(POSTS_API_URL).toString();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getPostsByUser(@RequestParam Long userId) {
        String url = POSTS_API_URL + "?user_id=" + userId;
        String response = goRestWebclientService.getData(url).toString();
        return ResponseEntity.ok(response);
    }
}
