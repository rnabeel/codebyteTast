package com.task.jwt.security.controller;

import com.task.jwt.security.service.GoRestWebclientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final String COMMENTS_API_URL = "/v2/comments";
    private final GoRestWebclientService goRestWebclientService;
    @GetMapping
    public ResponseEntity<String> getAllComments() {
        String response = goRestWebclientService.getData(COMMENTS_API_URL).toString();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post")
    public ResponseEntity<String> getCommentsByPost(@RequestParam Long postId) {
        String url = COMMENTS_API_URL + "?post_id=" + postId;
        String response = goRestWebclientService.getData(url).toString();
        return ResponseEntity.ok(response);
    }
}
