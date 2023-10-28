package TSM.demo.repository;


import TSM.demo.domain.place.*;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.AssertFalse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceRepositoryTest {


    @Autowired
    public EntityManager em;
    @Autowired
    public PlaceRepository placeRepository;
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
    public void Restaurant테스트1() {
        Place restaurant=new Restaurant("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        placeRepository.save(restaurant);
        Restaurant findRestaurant = placeRepository.findRestaurant(1);
        Assertions.assertThat(findRestaurant.getName()).isEqualTo(restaurant.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void Restaurant테스트2() {
        Place restaurant1=new Restaurant("학식","먹으면 식중독걸림","www.konkuk.ac.kr");
        Place restaurant2=new Restaurant("세종대학식","오므라이스 2500원임","www.konkuk.ac.kr");
        placeRepository.save(restaurant1);
        placeRepository.save(restaurant2);
        List<Restaurant> allRestaurant = placeRepository.findAllRestaurant();
        for (Restaurant restaurant : allRestaurant) {
            System.out.println("restaurant = " + restaurant.getName());
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void Room테스트1() {
        Place room=new Room("487","사람 많으면 이상한 냄새남, 에어컨 이상해서 여름에 죽을 맛임","www.konkuk.ac.kr");
        placeRepository.save(room);
        Room findRoom = placeRepository.findRoom(1);
        Assertions.assertThat(findRoom.getName()).isEqualTo(room.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void Room테스트2() {
        Place room1=new Room("487","사람 많으면 이상한 냄새남, 에어컨 이상해서 여름에 죽을 맛임","www.konkuk.ac.kr");
        Place room2=new Room("501","가면 지박령들 있음","www.konkuk.ac.kr");

        placeRepository.save(room1);
        placeRepository.save(room2);
        List<Room> allRestaurant = placeRepository.findAllRoom();
        for (Room room : allRestaurant) {
            System.out.println("room.getName() = " + room.getName());
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void Transport테스트1() {
        Place transport=new Transport("따릉이","공학관 맛에 쌓였음","www.konkuk.ac.kr");
        placeRepository.save(transport);
        Transport findTransport = placeRepository.findTransport(transport.getId());
        Assertions.assertThat(findTransport.getName()).isEqualTo(transport.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void Transport테스트2() {
        Place transport1=new Transport("따릉이","공학관 맛에 쌓였음","www.konkuk.ac.kr");
        Place transport2=new Transport("2호선","지옥철 경험 ㅆ가능","www.konkuk.ac.kr");
        placeRepository.save(transport1);
        placeRepository.save(transport2);
        List<Transport> allTransport = placeRepository.findAllTransport();
        for (Transport transport : allTransport) {
            System.out.println("transport.getName() = " + transport.getName());
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void TravelPlace테스트1() {
        Place travelPlace=new TravelPlace("일감호","외부인에게 학교를 소개할때 꼭 등장하지만 정작 학생들은 제일 싫어하는 ","www.konkuk.ac.kr");
        placeRepository.save(travelPlace);
        TravelPlace findTravelPlace = placeRepository.findTravelPlace(1);
        Assertions.assertThat(findTravelPlace.getName()).isEqualTo(travelPlace.getName());
    }

    @Test
    @Transactional(readOnly = false)
    public void TravelPlace테스트2() {
        Place transport1=new Transport("따릉이","공학관 맛에 쌓였음","www.konkuk.ac.kr");
        Place transport2=new Transport("2호선","지옥철 경험 ㅆ가능","www.konkuk.ac.kr");
        placeRepository.save(transport1);
        placeRepository.save(transport2);
        List<Transport> allTransport = placeRepository.findAllTransport();
        for (Transport transport : allTransport) {
            System.out.println("transport.getName() = " + transport.getName());
        }

    }
}
