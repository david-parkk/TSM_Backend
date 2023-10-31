package TSM.demo.repository;

import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.place.Course;
import TSM.demo.repository.query.MatchingQueryDto;
import jakarta.persistence.EntityManager;
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


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingRepositoryTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public MatchingRepository matchingRepository;
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

    LocalDateTime currentDateTime = LocalDateTime.now();

    // LocalDateTime을 Timestamp로 변환
    Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);



    // 특정 날짜와 시간을 사용하여 LocalDateTime 생성


    @Test
    @Transactional(readOnly = false)
    public void MatchingRepository테스트1(){
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 30, 12, 0, 0); // 예시: 2023년 10월 30일 12:00:00
        Timestamp specificTimestamp = Timestamp.valueOf(specificDateTime);
        Matching matching1=new Matching(1, State.FAIL,1,1,specificTimestamp,specificTimestamp,3,1);
        Matching matching2=new Matching(1,State.FAIL,2,1,specificTimestamp,specificTimestamp,3,1);
        Matching matching3=new Matching(1,State.FAIL,3,1,specificTimestamp,specificTimestamp,3,1);
        Matching matching4=new Matching(1,State.FAIL,3,2,specificTimestamp,specificTimestamp,3,1);
        Matching matching5=new Matching(1,State.FAIL,3,4,specificTimestamp,specificTimestamp,3,1);
        matchingRepository.save(matching1);
        matchingRepository.save(matching2);
        matchingRepository.save(matching3);
        matchingRepository.save(matching4);
        matchingRepository.save(matching5);


        List<Matching> allBySickId = matchingRepository.findAllBySickId(1);
        for (Matching matching : allBySickId) {
            System.out.println("matching = " + matching);
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void MatchingRepository테스트1_course포함전체조회1(){
        Course course1 =new Course("TSM 코스","광진구","성공시 박지원 퇴근1","www..wwww.www");
        Course course2 =new Course("TSM 코스","광진구","성공시 박지원 퇴근2","www..wwww.www");
        Course course3 =new Course("TSM 코스","광진구","성공시 박지원 퇴근3","www..wwww.www");
        em.persist(course1);
        em.persist(course2);
        em.persist(course3);
        int id1=course1.getId();
        int id2=course2.getId();
        int id3=course3.getId();
                
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 30, 12, 0, 0); // 예시: 2023년 10월 30일 12:00:00
        Timestamp specificTimestamp = Timestamp.valueOf(specificDateTime);
        Matching matching1=new Matching(1, State.FAIL,1,1,specificTimestamp,specificTimestamp,0,id1);
        Matching matching2=new Matching(1,State.FAIL,1,1,specificTimestamp,specificTimestamp,0,id2);
        Matching matching3=new Matching(1,State.FAIL,3,1,specificTimestamp,specificTimestamp,1,id3);
        em.persist(matching1);
        em.persist(matching2);
        em.persist(matching3);

        List<MatchingQueryDto> allInfoByVolunteerId = matchingRepository.findAllInfoByVolunteerId(1);
        for (MatchingQueryDto matchingQueryDto : allInfoByVolunteerId) {
            System.out.println("matchingQueryDto = " + matchingQueryDto);
            System.out.println("matchingQueryDto.getCourse().getName() = " + matchingQueryDto.getCourse().getName());
            System.out.println("matchingQueryDto.getCourse().getDescription() = " + matchingQueryDto.getCourse().getDescription());
        }


    }
    @Test
    @Transactional(readOnly = false)
    public void MatchingRepository테스트1_course포함전체조회2(){
        Course course1 =new Course("TSM 코스","광진구","성공시 박지원 퇴근1","www..wwww.www");
        Course course2 =new Course("TSM 코스","광진구","성공시 박지원 퇴근2","www..wwww.www");
        Course course3 =new Course("TSM 코스","광진구","성공시 박지원 퇴근3","www..wwww.www");
        em.persist(course1);
        em.persist(course2);
        em.persist(course3);
        int id1=course1.getId();
        int id2=course2.getId();
        int id3=course3.getId();

        LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 30, 12, 0, 0); // 예시: 2023년 10월 30일 12:00:00
        Timestamp specificTimestamp = Timestamp.valueOf(specificDateTime);
        Matching matching1=new Matching(1, State.FAIL,1,1,specificTimestamp,specificTimestamp,0,id1);
        Matching matching2=new Matching(1,State.FAIL,1,1,specificTimestamp,specificTimestamp,0,id2);
        Matching matching3=new Matching(1,State.FAIL,3,1,specificTimestamp,specificTimestamp,1,id3);
        em.persist(matching1);
        em.persist(matching2);
        em.persist(matching3);

        List<MatchingQueryDto> allInfoByVolunteerId = matchingRepository.findAllInfoWithCourseByVolunteerId(1);
        for (MatchingQueryDto matchingQueryDto : allInfoByVolunteerId) {
            System.out.println("matchingQueryDto = " + matchingQueryDto);
            System.out.println("matchingQueryDto.getCourse().getName() = " + matchingQueryDto.getCourse().getName());
            System.out.println("matchingQueryDto.getCourse().getDescription() = " + matchingQueryDto.getCourse().getDescription());
        }


    }
}
