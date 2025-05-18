package vn.khanhduc.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.khanhduc.elearning.dto.request.UserCreationRequest;
import vn.khanhduc.elearning.dto.response.UserCreationResponse;
import vn.khanhduc.elearning.dto.response.UserDetailResponse;
import vn.khanhduc.elearning.service.UserService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public UserCreationResponse createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDetailResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserDetailResponse> getAllUsers(){
        return userService.getAllUsers();
    }

}
