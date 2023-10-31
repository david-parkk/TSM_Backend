package TSM.demo.controller;

import TSM.demo.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public String googleLogin(@RequestParam String code, @PathVariable String registrationId, HttpSession httpSession) {
        String email = loginService.socialLogin(code, registrationId);
        //세션 데이터 추가
        httpSession.setAttribute("email", email);
        return "forward:/";
    }
}