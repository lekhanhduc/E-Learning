package vn.khanhduc.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.khanhduc.elearning.dto.request.UserCreationRequest;
import vn.khanhduc.elearning.dto.response.UserCreationResponse;
import vn.khanhduc.elearning.dto.response.UserDetailResponse;
import vn.khanhduc.elearning.entity.User;
import vn.khanhduc.elearning.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreationResponse createUser(UserCreationRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());
        if(byEmail.isPresent()) {
            throw new RuntimeException("Email existed");
        }
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return UserCreationResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserDetailResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> UserDetailResponse.builder()
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .avatar(user.getAvatar())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}

