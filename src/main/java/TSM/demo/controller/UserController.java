package TSM.demo.controller;


import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public String createUser(User user, HttpSession httpSession, @RequestParam("walk") int walk,
                             @RequestParam("see") int see,
                             @RequestParam("talk") int talk,
                             @RequestParam("listen") int listen,
                             @RequestParam("depression") int depression,
                             @RequestParam("bipolar_disorder") int bipolarDisorder,
                             @RequestParam("iq") int iq) {
        UserHealth userHealth = new UserHealth(walk, see, talk, listen, depression, bipolarDisorder, iq);
        user.setUserHealth(userHealth);
        userService.join(user);
        httpSession.setAttribute("email", user.getEmail());
        return "forward:/";
    }
}
