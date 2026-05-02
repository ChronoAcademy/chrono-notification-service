package com.chronoacademy.notification.feign;

import com.chronoacademy.notification.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = spring.application.name du auth-service de ton collègue ASKRI
@FeignClient(name = "auth-service", url = "http://localhost:8085")
public interface UserServiceClient {

    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable("userId") String userId);
}
