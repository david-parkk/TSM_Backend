package TSM.demo.controller;

import TSM.demo.domain.place.Course;
import TSM.demo.service.CourseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/course")
    public List<Course> getCourse(HttpSession httpSession) {
        //세션 데이터 추가
        String email = httpSession.getAttribute("email").toString();
        return courseService.recommendCourse(email);
    }
}
