package TSM.demo.controller;

import TSM.demo.repository.query.LoginResponseDto;
import TSM.demo.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping()
    public String login() {
        return "login";
    }

    @GetMapping(value = "/oauth2/code/{registrationId}", produces = "application/json")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId, HttpSession httpSession, HttpServletResponse response) throws IOException {
        LoginResponseDto loginResponseDto = loginService.socialLogin(code, registrationId);
        // 회원
        httpSession.setAttribute("email", loginResponseDto.getEmail());
        httpSession.setAttribute("name", loginResponseDto.getName());
        httpSession.setAttribute("oauthId", loginResponseDto.getOauthId());
        if (loginService.isRegisteredUser(loginResponseDto.getOauthId())) {
            //세션 데이터 추가
            httpSession.setAttribute("userHealth", loginResponseDto.getUserHealth());
            // unwell, volunteer 페이지 구분
            if (loginResponseDto.getIsVolunteer() == 1) {
                response.sendRedirect("/matching");
                return;
            } else {
                response.sendRedirect("/course");
                return;
            }
        }
        //비회원
        response.sendRedirect("/auth/signup");
    }
}