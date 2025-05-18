package vn.khanhduc.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.khanhduc.elearning.dto.request.SignInRequest;
import vn.khanhduc.elearning.dto.response.SignInResponse;
import vn.khanhduc.elearning.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public SignInResponse login(@RequestBody SignInRequest request) {
        return authenticationService.login(request);
    }

}
