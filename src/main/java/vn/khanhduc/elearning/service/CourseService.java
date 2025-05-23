package vn.khanhduc.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.khanhduc.elearning.dto.request.CourseCreationRequest;
import vn.khanhduc.elearning.dto.response.CourseCreationResponse;
import vn.khanhduc.elearning.dto.response.CourseDetailResponse;
import vn.khanhduc.elearning.entity.Course;
import vn.khanhduc.elearning.repository.CourseRepository;
import vn.khanhduc.elearning.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseCreationResponse creation(CourseCreationRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 1 Tạo khóa học
        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .courseCover(request.getCourseCover())
                .author(user)
                .build();

        // 2 Lưu vào db
        courseRepository.save(course);

        // 3 Trả về response
        return CourseCreationResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .courseCover(course.getCourseCover())
                .authorName(course.getAuthor().getFirstName() +" "+ course.getAuthor().getLastName())
                .build();
    }

    public List<CourseDetailResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> CourseDetailResponse.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .description(course.getDescription())
                        .price(course.getPrice())
                        .courseCover(course.getCourseCover())
                        .authorName(course.getAuthor().getFirstName() +" "+ course.getAuthor().getLastName())
                        .build())
                .toList();
    }

    public String delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        // xử lý thêm
        courseRepository.delete(course);
        return "Deleted successfully";
    }

}
