package TSM.demo.service;

import TSM.demo.domain.User;
import TSM.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public int join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    //회원 조회
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public User findOne(int userId) {
        return userRepository.findOne(userId);
    }
}
