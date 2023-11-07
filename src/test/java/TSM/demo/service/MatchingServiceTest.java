package TSM.demo.service;

import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.*;
import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.query.MatchingDto;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public MatchingService matchingService;
    @Autowired
    public MatchingRepository matchingRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PlaceService placeService;
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

    LocalDateTime currentDateTime = LocalDateTime.now();

    // LocalDateTime을 Timestamp로 변환
    Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);

    @Test
    @Transactional(readOnly = false)
    public void MatchingService테스트1(){
        Course course=new Course("코스1","서울","설명1","주소1");
        em.persist(course);

        User user1=new User("이메일1","휴대폰번호1",1,"봉사자1","토큰1");
        User user2=new User("이메일1","휴대폰번호1",1,"봉사자2","토큰1");
        User user3=new User("이메일2","휴대폰번호2",0,"unwell1","토큰2");
        User user4=new User("이메일2","휴대폰번호2",0,"unwell2","토큰2");
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        matchingService.requestHelpByUnwell(user3.getId(),1,currentTimestamp,currentTimestamp, course.getId());
        List<Matching> allByGroupId = matchingService.findAllByGroupId(1);
        for (Matching matching : allByGroupId) {
            System.out.println("matching.getVolunteerId() = " + matching.getVolunteerId());
            System.out.println("matching.getState() = " + matching.getState());
        }
        matchingService.addMatchingByUnwell(user3.getId(),1);
        List<Matching> allByGroupId1 = matchingService.findAllByGroupId(1);
        for (Matching matching : allByGroupId1) {
            System.out.println("matching.getVolunteerId() = " + matching.getVolunteerId());
            System.out.println("matching.getState() = " + matching.getState());
        }

        matchingService.addMatchingByUnwell(user4.getId(),1);
        List<Matching> allByGroupId2 = matchingService.findAllByGroupId(1);
        System.out.println("size: "+allByGroupId2.size());
        for (Matching matching : allByGroupId2) {

            System.out.println("matching.getVolunteerId() = " + matching.getVolunteerId());
            System.out.println("matching.getState() = " + matching.getState());
        }
    }

    @Test
    @Transactional
    public void MatchingService테스트2(){
        User volunteer1=new User("wwww1","000-0000-0000",1,"봉사자1","token1");
        User
                volunteer2=new User("wwww2","000-0000-0000",1,"봉사자2","token2");

        User unwell1=new User("wwww3","000-0000-0000",0,"아픈사람1","token1");
        User unwell2=new User("wwww4","000-0000-0000",0,"아픈사람2","token1");

        UserHealth userHealth1=new UserHealth(1,1,1,1,1,1,1);
        UserHealth userHealth2=new UserHealth(1,1,1,1,1,1,1);
        UserHealth userHealth3=new UserHealth(1,1,1,1,1,1,1);
        UserHealth userHealth4=new UserHealth(1,1,1,1,1,1,1);
        volunteer1.setUserHealth(userHealth1);
        volunteer2.setUserHealth(userHealth2);
        unwell1.setUserHealth(userHealth3);
        unwell2.setUserHealth(userHealth4);

        em.persist(volunteer1);
        em.persist(volunteer2);
        em.persist(unwell1);
        em.persist(unwell2);
        Course course1=new Course("서울 가성비 여행 코스","서울특별시","서울을 가장 값싼 가격에 여행 갈수 있는 여행 코스 입니다","www.seoul.trip");
        Course course2=new Course("건대 공대 코스","서울특별시","건입 ","www.seoul.trip");
        UserHealth userHealth5=new UserHealth(1,1,1,1,1,1,1);
        UserHealth userHealth6=new UserHealth(1,1,1,1,1,1,1);
        TravelPlace travelPlace1=new TravelPlace("학식","먹으면 식중독걸림","www.konkuk.ac.kr");

        travelPlace1.setCourse(course1);
        course1.getTravelPlaceList().add(travelPlace1);
        course1.setUserHealth(userHealth5);
        course2.setUserHealth(userHealth6);
        em.persist(travelPlace1);
        em.persist(course1);
        em.persist(course2);
        Matching matching=new Matching(1, null,unwell1.getId(),volunteer1.getId(),currentTimestamp,currentTimestamp,1,course1.getId());
        UserHealth userHealth7=new UserHealth(1,1,1,1,1,1,1);
        matching.setUserHealth(userHealth7);
        em.persist(matching);
        //matchingService.requestHelpByUnwell(unwell1.getId(),1,currentTimestamp,currentTimestamp,course1.getId());

        matchingService.addMatchingByUnwell(volunteer1.getId(),1);


        List<Matching> matchings = matchingService.findAllByVolunteerId(volunteer1.getId());
        Assertions.assertThat(matchings.size()).isEqualTo(1);

        List<MatchingDto>matchingDtos=new ArrayList<>();
        for(Matching matching0:matchings){
            matchingDtos.add(new MatchingDto(matching0));
        }
        List<Course>courses=new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Transport> transports = new ArrayList<>();
        List<TravelPlace> travelPlaces=new ArrayList<>();


        restaurants.addAll(placeService.findAllRestaurantByMatching(matchings));
        rooms.addAll(placeService.findAllRoomByMatching(matchings));
        transports.addAll(placeService.findAllTransportByMatching(matchings));
        travelPlaces.addAll(placeService.findAllTravelPlaceByMatching(matchings));
        courses.addAll(courseService.findAllCourseByMatching(matchings));
        for(MatchingDto matchingDto:matchingDtos) {
            if (matchingDto.getRequest_type() == 1) {
                for (Course course : courses) {
                    if (course.getId() == matchingDto.getRequest_id()) {
                        matchingDto.setCourse(course);
                        break;
                    }
                }
            } else if (matchingDto.getRequest_type() == 2) {
                for (Restaurant restaurant : restaurants) {
                    if (restaurant.getId() == matchingDto.getRequest_id()) {
                        matchingDto.setRestaurant(restaurant);
                        break;
                    }
                }
            } else if (matchingDto.getRequest_type() == 3) {
                for (Room room : rooms) {
                    if (room.getId() == matchingDto.getRequest_id()) {
                        matchingDto.setRoom(room);
                        break;
                    }
                }
            } else if (matchingDto.getRequest_type() == 4) {
                for (Transport transport : transports) {
                    if (transport.getId() == matchingDto.getRequest_id()) {
                        matchingDto.setTransport(transport);
                        break;
                    }
                }
            } else if (matchingDto.getRequest_type() == 5) {
                for (TravelPlace travelPlace : travelPlaces) {
                    if (travelPlace.getId() == matchingDto.getRequest_id()) {
                        System.out.println("set!!");
                        matchingDto.setTravelPlace(travelPlace);
                        break;
                    }
                }
            }
            //matchingService.findAllByVolunteerId(volunteer1.getId()).stream()
            //        .forEach(o->matchingDtos.add(new MatchingDto(o)));
        }
        Assertions.assertThat(matchingDtos.get(0).getCourse().getName()).isEqualTo(course1.getName());

    }
}
