package TSM.demo.service;

import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.Course;
import TSM.demo.repository.CourseRepository;
import TSM.demo.repository.UserRepository;
import TSM.demo.repository.query.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<RecommendCourseResponseDto> recommendCourse(String email) {
        List<RecommendCourseResponseDto> recommendedCourse = new ArrayList<>();
        UserHealth userHealth = (UserHealth) Hibernate.unproxy(userRepository.findByEmail(email).getUserHealth());

        List<Course> courseList = courseRepository.findAll();
        for (Course course : courseList) {
            UserHealth courseDifficulty = (UserHealth) Hibernate.unproxy(course.getUserHealth());
            // user 가 갈 수 있는 course 만 리턴
            if(userHealth.isPossibleCourse(courseDifficulty)) {
                recommendedCourse.add(new RecommendCourseResponseDto(course.getName(), course.getUrl()));
            }
        }
        return recommendedCourse;
    }

    public CourseDto findCourse(String courseName) {
        return courseRepository.findByName(courseName).toDto();
    }
}
