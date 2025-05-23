package vn.khanhduc.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.khanhduc.elearning.service.CloudinaryService;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload/media")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return cloudinaryService.uploadFile(file);
    }

}
