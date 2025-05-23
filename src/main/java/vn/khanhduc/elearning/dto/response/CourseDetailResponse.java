package vn.khanhduc.elearning.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CourseDetailResponse {
    private Long id;
    private String name;
    private String description;
    private String courseCover;
    private BigDecimal price;
    private String authorName;
}
