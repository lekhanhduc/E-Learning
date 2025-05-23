package vn.khanhduc.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.khanhduc.elearning.dto.request.CourseCreationRequest;
import vn.khanhduc.elearning.dto.response.ApiResponse;
import vn.khanhduc.elearning.dto.response.CourseCreationResponse;
import vn.khanhduc.elearning.dto.response.CourseDetailResponse;
import vn.khanhduc.elearning.service.CourseService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseCreationResponse createCourse(@RequestBody CourseCreationRequest request) {
        return courseService.creation(request);
    }

    @GetMapping("/fetch-all")
    public ApiResponse<List<CourseDetailResponse>> getAllCourses() {
        return ApiResponse.<List<CourseDetailResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(courseService.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCourse (@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(courseService.delete(id))
                .build();
    }

}
