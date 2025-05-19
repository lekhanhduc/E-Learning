package vn.khanhduc.elearning.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vn.khanhduc.elearning.dto.request.RefreshTokenRequest;
import vn.khanhduc.elearning.dto.request.SignInRequest;
import vn.khanhduc.elearning.dto.response.RefreshTokenResponse;
import vn.khanhduc.elearning.dto.response.SignInResponse;
import vn.khanhduc.elearning.service.AuthenticationService;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public SignInResponse login(@RequestBody SignInRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        try {
            authenticationService.logout(token.replace("Bearer ", ""));
        } catch (ParseException e) {
            log.error("Logout error");
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest request) throws ParseException {
        return authenticationService.refreshToken(request);
    }
    
}
