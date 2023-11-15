package TSM.demo;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.repository.UserRepository;
import TSM.demo.service.UserService;
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
    @Autowired UserService userService;

    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user=new User("naver@naver.com","+8210-000-0000",1,"김건대","XXXX");
        UserHealth userHealth = new UserHealth(0,1,0,1,0,1,1);

        //when
        int saveId = userService.join(user, userHealth);

        //then
        assertEquals(user, userRepository.findOne(saveId));
        assertEquals(user, userRepository.findByName(user.getName()));
        assertEquals(user, userRepository.findByEmail(user.getEmail()));
        assertEquals(userHealth, userRepository.findByEmail(user.getEmail()).getUserHealth());
        System.out.println("hi");
    }
}
