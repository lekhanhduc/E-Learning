package vn.khanhduc.elearning.dto.request;

import lombok.Getter;

@Getter
public class CourseCreationRequest {
    private String name;
    private String description;
    private String courseCover;
    private String price;
}
