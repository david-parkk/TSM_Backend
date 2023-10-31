package TSM.demo.controller;

import TSM.demo.repository.query.CourseDto;
import TSM.demo.service.CourseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

//    @GetMapping()
//    public List<CourseQueryDto> requestCourseList(HttpSession httpSession) {
//        //세션 데이터 추가
//        String email = httpSession.getAttribute("email").toString();
//        return courseService.recommendCourse(email);
//    }

    @GetMapping()
    public List<String> requestCourseNameList(HttpSession httpSession) {
        //세션 데이터 추가
        String email = httpSession.getAttribute("email").toString();
        return courseService.recommendCourse(email);
    }

    @GetMapping("/description")
    public CourseDto requestCourse(@RequestParam(value = "name") String courseName) {
        return courseService.findCourse(courseName);
    }
}
