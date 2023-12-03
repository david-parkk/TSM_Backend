package TSM.demo.controller;

import TSM.demo.service.RatingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping()
    public void rateUser(HttpSession session, @RequestParam String receiverEmail, @RequestParam int star) {
        String email = session.getAttribute("email").toString();
        ratingService.rateUser(email, receiverEmail, star);
    }


}