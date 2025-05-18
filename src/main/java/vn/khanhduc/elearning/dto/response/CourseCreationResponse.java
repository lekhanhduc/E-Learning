package vn.khanhduc.elearning.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseCreationResponse {
    private Long id;
    private String name;
    private String description;
    private String courseCover;
    private String price;
}
