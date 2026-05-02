package com.chronoacademy.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {

        // Accepte n'importe quel userId
        return ResponseEntity.ok(Map.of(
                "id",       userId,
                "username", "user_" + userId,
                "email",    userId + "@chrono.com",
                "role",     "STUDENT"
        ));
    }
}