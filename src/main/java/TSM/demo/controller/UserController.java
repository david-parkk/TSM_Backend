package TSM.demo.controller;


import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    //회원가입 페이지로 이동
    @GetMapping("/signup")
    public String SignupForm() {
        return "login_signup_select";
    }

    //회원가입 POST 요청
    @PostMapping("/signup")
    public String createUser(HttpSession httpSession,
                             @RequestBody Map<String, String> data) {
        String name = (String) httpSession.getAttribute("name");
        String email = (String) httpSession.getAttribute("email");
        String phoneNum = data.get("phoneNum");
        int isVolunteer = 0;
        if (data.get("walk") == null) isVolunteer = 1;
        String oauthId = (String) httpSession.getAttribute("oauthId");
        User user = new User(email, data.get("phoneNum"), isVolunteer, name, oauthId);
        try {
            userService.join(user,
                    new UserHealth(Integer.parseInt(data.get("walk")),
                            Integer.parseInt(data.get("see")),
                            Integer.parseInt(data.get("talk")),
                            Integer.parseInt(data.get("listen")),
                            Integer.parseInt(data.get("depression")),
                            Integer.parseInt(data.get("bipolar_disorder")),
                            Integer.parseInt(data.get("iq"))));
            httpSession.setAttribute("userHealth", user.getUserHealth());
        } catch (NullPointerException e) {
            userService.join(user, null);
        }
        return "unwell_search";
    }


    @GetMapping("/login_signup_volunteer")
    public String signupVolunteer(Model model, HttpSession httpSession) {
        model.addAttribute("email", httpSession.getAttribute("email"));
        model.addAttribute("username", httpSession.getAttribute("username"));
        return "login_signup_volunteer";
    }

    @GetMapping("/login_signup_unwell")
    public String signupUnwell(Model model, HttpSession httpSession) {
        model.addAttribute("email", httpSession.getAttribute("email"));
        model.addAttribute("username", httpSession.getAttribute("username"));
        return "login_signup_unwell";
    }

}
