package TSM.demo.controller;

import TSM.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        loginService.socialLogin(code, registrationId);
    }
}