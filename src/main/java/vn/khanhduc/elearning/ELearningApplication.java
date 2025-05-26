package vn.khanhduc.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ELearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(ELearningApplication.class, args);
    }
}
