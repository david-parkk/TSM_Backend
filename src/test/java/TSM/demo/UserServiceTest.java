package TSM.demo;

import TSM.demo.domain.User;
import TSM.demo.repository.UserRepository;
import TSM.demo.service.UserService;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired EntityManager em;
    @Autowired UserService userService;

    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user=new User("naver@naver.com","+8210-000-0000",true,"김건대","XXXX");

        //when
        int saveId = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(saveId));
        assertEquals(user, userRepository.findByName(user.getName()));
    }
}
