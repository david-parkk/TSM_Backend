package TSM.demo.controller;


import TSM.demo.domain.User;
import TSM.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    //회원가입 페이지로 이동
    @GetMapping("/signup")
    public String SignupForm() {
        return "auth/signup";
    }

    //회원가입 POST 요청
    @PostMapping("/signup")
    public String createUser(User user) {
        userService.join(user);
        return "redirect:/";
    }
}
