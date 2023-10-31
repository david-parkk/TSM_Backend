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
    public String createUser(User user, HttpSession httpSession, @RequestParam(value = "walk", required = false) Integer walk,
                             @RequestParam(value = "see", required = false) Integer see,
                             @RequestParam(value = "talk", required = false) Integer talk,
                             @RequestParam(value = "listen", required = false) Integer listen,
                             @RequestParam(value = "depression", required = false) Integer depression,
                             @RequestParam(value = "bipolar_disorder", required = false) Integer bipolarDisorder,
                             @RequestParam(value = "iq", required = false) Integer iq) {
        try {
            userService.join(user, new UserHealth(walk, see, talk, listen, depression, bipolarDisorder, iq));
        } catch (NullPointerException e) {
            userService.join(user, null);
        }
        httpSession.setAttribute("email", user.getEmail());
        return "forward:/";
    }
}
