package vn.khanhduc.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.khanhduc.elearning.dto.request.CourseCreationRequest;
import vn.khanhduc.elearning.dto.response.CourseCreationResponse;
import vn.khanhduc.elearning.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseCreationResponse creation(CourseCreationRequest request) {
        // 1 Tạo khóa học


        // 2 Lưu vào db


        // 3 Trả về response

        return null;
    }

}
