package TSM.demo.service;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public int join(User user, UserHealth userHealth) {
        user.setUserHealth(userHealth);
        userRepository.save(user);
        return user.getId();
    }

    //회원 조회
    @Transactional(readOnly = true)
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(int userId) {
        return userRepository.findOne(userId);
    }
}
