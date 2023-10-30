package TSM.demo.repository;

import TSM.demo.domain.place.Course;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {


    @Autowired
    public EntityManager em;
    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    TransactionStatus status;
    @BeforeEach
    void beforeEach() {
        // 트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }
    @AfterEach
    void afterEach() {
        // 트랜잭션 롤백
        transactionManager.rollback(status);
    }

    @Test
    @Transactional(readOnly = false)
    public void CourseRepository테스트1(){
        Course course=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        courseRepository.save(course);
        Course findCourse = courseRepository.findOneById(course.getId());
        Assertions.assertThat(findCourse.getName()).isSameAs(course.getName());

    }

    @Test
    @Transactional(readOnly = false)
    public void CourseRepository테스트2(){
        Course course1=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        Course course2=new Course("건대 공대 코스","서울특별시","건입 ","www.seoul.trip");
        courseRepository.save(course1);
        courseRepository.save(course2);
        List<Course> courseList = courseRepository.findAll();
        for (Course course : courseList) {
            System.out.println("course.getName() = " + course.getName());
        }
    }

}
