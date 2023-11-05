package TSM.demo.controller;

import TSM.demo.domain.Matching;
import TSM.demo.repository.query.MatchingDto;

import TSM.demo.service.MatchingService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping("/matchings")
    public List<MatchingDto> showAllMatchingById(@RequestBody HashMap<String, Integer> map) {
        List<MatchingDto> matchingDtos=new ArrayList<>();
        if(map.get("isVolunteer")==1){
            matchingService.findAllByVolunteerId(map.get("id")).stream()
                    .forEach(o->matchingDtos.add(new MatchingDto(o)));

        }
        return matchingDtos;

    }

    @PostMapping("/select")
    public CreateMatchingResponse CreateMatchingRequest(@RequestBody @Valid MatchingSelect matchingSelect) {
        if(matchingSelect.getIsVolunteer()==1)
            matchingService.addMatchingByUnwell(matchingSelect.getUserId(),matchingSelect.getMatchingGroupId());

        return new CreateMatchingResponse("success");
    }

    @PostMapping("/approve")
    public CreateMatchingResponse selectMatchingRequest(@RequestBody HashMap<String, Integer> map) {
        matchingService.selectMatchingByVolunteer(map.get("id"), map.get("Group_id"));
        return new CreateMatchingResponse("success");
    }

    @PostMapping("/request")
    public String requestHelp(@RequestParam int sickId,
                              @RequestParam String startTime,
                              @RequestParam String endTime,
                              @RequestParam int requestType,
                              @RequestParam int requestId
            , HttpSession httpSession) {
        //int sickId = (int) httpSession.getAttribute("userId");
        matchingService.requestHelpByUnwell(sickId, requestType, Timestamp.valueOf(startTime), Timestamp.valueOf(endTime), requestId);
        return "/";
    }

    @Setter
    @Getter
    @ToString
    static class MatchingRequest {
        private int sickId;
        private int requestType;
        private int requestId;
        private Timestamp startTime;
        private Timestamp endTime;

        public MatchingRequest() {

        }

        public MatchingRequest(int sickId, int requestType, int requestId, Timestamp startTime, Timestamp endTime) {
            this.sickId = sickId;
            this.requestType = requestType;
            this.requestId = requestId;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    @Setter
    @Getter
    @ToString
    static class MatchingSelect {
        private int matchingGroupId;
        private int isVolunteer;
        private int userId;
        public MatchingSelect(int matchingGroupId,int isVolunteer,int userId){
            this.matchingGroupId=matchingGroupId;
            this.isVolunteer=isVolunteer;
            this.userId=userId;
        }


    }
    @Setter
    @Getter
    @ToString
    static class CreateMatchingResponse {
        private String result;

        public CreateMatchingResponse(String result) {
            this.result = result;
        }
    }
}
