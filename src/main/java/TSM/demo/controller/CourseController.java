package TSM.demo.controller;

import TSM.demo.repository.query.CourseDto;
import TSM.demo.repository.query.RecommendCourseResponseDto;
import TSM.demo.service.CourseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    public ModelAndView requestCourseList(ModelAndView mav, HttpSession httpSession) {
        //세션 데이터 추가
        String email = httpSession.getAttribute("email").toString();

        mav.setViewName("unwell_search");

        List<RecommendCourseResponseDto> recommendCourseResponseDtos=courseService.recommendCourse(email);

        mav.addObject("courses",recommendCourseResponseDtos);
        

        return mav;
    }

    @GetMapping("/description/{name}")
    public ModelAndView requestCourseDescription(@PathVariable("name") String name, ModelAndView mav) {
        mav.setViewName("unwell_deepsearch");

        CourseDto courseDto=courseService.findCourse(name);

        mav.addObject("course",courseDto);
        return mav;
    }


//    @GetMapping("/description/{name}")
//    public CourseDto requestCourseDescription(@PathVariable("name") String name) {
//        return courseService.findCourse(name);
//    }

}