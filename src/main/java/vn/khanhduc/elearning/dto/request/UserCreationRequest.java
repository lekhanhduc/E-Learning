package vn.khanhduc.elearning.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
