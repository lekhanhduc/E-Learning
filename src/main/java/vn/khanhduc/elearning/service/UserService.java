package vn.khanhduc.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.khanhduc.elearning.dto.request.UserCreationRequest;
import vn.khanhduc.elearning.dto.response.UserCreationResponse;
import vn.khanhduc.elearning.dto.response.UserDetailResponse;
import vn.khanhduc.elearning.entity.Role;
import vn.khanhduc.elearning.entity.User;
import vn.khanhduc.elearning.entity.UserHasRole;
import vn.khanhduc.elearning.repository.RoleRepository;
import vn.khanhduc.elearning.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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

        Optional<Role> role = roleRepository.findByName("USER");
        if(role.isEmpty()) {
            Role newRole = Role.builder()
                    .name("USER")
                    .build();
            roleRepository.save(newRole);
            user.setUserHasRoles(List.of(UserHasRole.builder()
                            .role(newRole)
                            .user(user)
                    .build()));
        }

        role.ifPresent(value -> user.setUserHasRoles(List.of(UserHasRole.builder()
                .role(value)
                .user(user)
                .build())));

        userRepository.save(user);

        return UserCreationResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("hasAuthority('USER, ADMIN')") // => ROLE_USER, ROLE_USER
    public List<UserDetailResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> UserDetailResponse.builder()
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .avatar(user.getAvatar())
                        .build())
                .toList();
    }

}

