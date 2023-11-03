package TSM.demo.service;

import TSM.demo.domain.Matching;
import TSM.demo.domain.User;
import TSM.demo.domain.place.Course;
import TSM.demo.repository.MatchingRepository;
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

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public MatchingService matchingService;
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
}
