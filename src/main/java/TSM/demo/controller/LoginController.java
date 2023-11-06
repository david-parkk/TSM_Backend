package TSM.demo.controller;

import TSM.demo.repository.query.LoginResponseDto;
import TSM.demo.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public String googleLogin(@RequestParam String code, @PathVariable String registrationId, HttpSession httpSession, HttpServletResponse response) throws IOException {
        LoginResponseDto loginResponseDto = loginService.socialLogin(code, registrationId);
        // 회원
        response.sendRedirect("/auth/signup");
        if (loginService.isRegisteredUser(loginResponseDto.getOauthId())) {
            //세션 데이터 추가
            httpSession.setAttribute("email", loginResponseDto.getEmail());
            httpSession.setAttribute("userHealth", loginResponseDto.getUserHealth());
            // unwell, volunteer 페이지 구분
            return "redirect:/";
        }
        //비회원
        return "redirect:/auth/signup";
    }
}