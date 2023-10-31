package TSM.demo.controller;

import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.query.MatchingQueryDto;
import TSM.demo.service.MatchingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping("/matchings")
    public List<MatchingQueryDto> showAllMatchingById(@RequestBody HashMap<String, Integer> map){
        return matchingService.findAllByVolunteerId(map.get("id"));
    }

}
