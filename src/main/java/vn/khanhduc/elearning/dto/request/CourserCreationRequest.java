package vn.khanhduc.elearning.dto.request;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CourserCreationRequest {
    private String name;
    private String description;
    private String courseCover;
    private BigDecimal price;
}
