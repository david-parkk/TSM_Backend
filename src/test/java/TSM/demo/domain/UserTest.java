package TSM.demo.domain;

import TSM.demo.controller.LoginController;
import TSM.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

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
    @Transactional
    public void user도메인테스트1(){
        User user=new User("naver@naver.com","+8210-000-0000",true,"김건대","XXXX");

        em.persist(user);
        em.flush();
        em.clear();
        int pk=user.getId();
        System.out.println(pk);
        User findUser=em.find(User.class,pk);
        Assertions.assertThat(findUser.getName()).isEqualTo("김건대");
    }

    @Test
    public void user_repository_test() {
        User user=new User("naver@naver.com","+8210-000-0000",true,"김건대","XXXX");

        
    }

    @Test
    @Transactional
    public void user와userhealth종속성테스트1(){
        User user=new User("naver@naver.com","+8210-000-0000",true,"김건대","XXXX");
        UserHealth userHealth=new UserHealth(1,1,1,1,1,1,1);
        user.setUserHealth(userHealth);
        em.persist(user);

        em.flush();
        em.close();
        User findUser=em.find(User.class,1);
        UserHealth findUserHealth=em.find(UserHealth.class,1);

        Assertions.assertThat(findUser.getUserHealth()).isSameAs(userHealth);
    }

    @Test
    @Transactional
    public void user와userhealth종속성테스트2(){
        User user=new User("naver@naver.com","+8210-000-0000",true,"김건대","XXXX");
        UserHealth userHealth=new UserHealth(1,1,1,1,1,1,1);
        user.setUserHealth(userHealth);
        em.persist(user);
        //em.persist(userHealth);
        em.flush();
        int pk=user.getId();
        User findUser=em.find(User.class,pk);
        em.remove(findUser);
        UserHealth findUserHealth = em.find(UserHealth.class, 1);
        Assertions.assertThat(findUserHealth).isNull();

    }
}
