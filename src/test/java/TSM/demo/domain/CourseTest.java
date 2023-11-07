package TSM.demo.domain;

import TSM.demo.domain.place.*;
import TSM.demo.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {

    @Autowired
    public EntityManager em;
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
    public void Course테스트1_restaurant연관관계(){
        Course course=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        em.persist(course);
        Restaurant restaurant1=new Restaurant("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        Restaurant restaurant2=new Restaurant("세종대학식","오므라이스 2500원임","www.konkuk.ac.kr");
        em.persist(restaurant1);
        em.persist(restaurant2);
        course.addRestaurant(restaurant1);
        course.addRestaurant(restaurant2);
        em.flush();
        em.close();
        restaurant1.setCourse(course);
        restaurant2.setCourse(course);
        Assertions.assertThat(restaurant1.getCourse().getRestaurantList().get(0).getName()).isEqualTo(restaurant1.getName());
        Assertions.assertThat(restaurant1.getCourse().getRestaurantList().get(1).getName()).isEqualTo(restaurant2.getName());

    }

    @Test
    @Transactional(readOnly = false)
    public void Course테스트1_room연관관계(){
        Course course=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        em.persist(course);
        Room room1=new Room("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        Room room2=new Room("세종대학식","오므라이스 2500원임","www.konkuk.ac.kr");
        em.persist(room1);
        em.persist(room2);
        course.addRoom(room1);
        course.addRoom(room2);
        em.flush();
        em.close();
        room1.setCourse(course);
        room2.setCourse(course);
        Assertions.assertThat(room1.getCourse().getRoomList().get(0).getName()).isEqualTo(room1.getName());
        Assertions.assertThat(room1.getCourse().getRoomList().get(1).getName()).isEqualTo(room2.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void Course테스트1_transport연관관계(){
        Course course=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        em.persist(course);
        Transport transport1=new Transport("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        Transport transport2=new Transport("세종대학식","오므라이스 2500원임","www.konkuk.ac.kr");
        em.persist(transport1);
        em.persist(transport2);
        course.addTransport(transport1);
        course.addTransport(transport2);
        em.flush();
        em.close();
        transport1.setCourse(course);
        transport2.setCourse(course);
        Assertions.assertThat(transport1.getCourse().getTransportList().get(0).getName()).isEqualTo(transport1.getName());
        Assertions.assertThat(transport1.getCourse().getTransportList().get(1).getName()).isEqualTo(transport2.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void Course테스트1_travelplace연관관계(){
        Course course=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        em.persist(course);
        TravelPlace travelPlace1=new TravelPlace("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        TravelPlace travelPlace2=new TravelPlace("세종대학식","오므라이스 2500원임","www.konkuk.ac.kr");
        em.persist(travelPlace1);
        em.persist(travelPlace2);
        course.addTravelPlace(travelPlace1);
        course.addTravelPlace(travelPlace2);
        em.flush();
        em.close();
        travelPlace1.setCourse(course);
        travelPlace2.setCourse(course);
        Assertions.assertThat(travelPlace1.getCourse().getTravelPlaceList().get(0).getName()).isEqualTo(travelPlace1.getName());
        Assertions.assertThat(travelPlace1.getCourse().getTravelPlaceList().get(1).getName()).isEqualTo(travelPlace2.getName());
    }
}
