package TSM.demo.controller;

import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.query.MatchingQueryDto;
import TSM.demo.service.MatchingService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
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

    @PostMapping("/matching")
    public CreateMatchingResponse CreateMatchingRequest(@RequestBody @Valid MatchingRequest matchingRequest){
        matchingService.addMatchingByVolunteer(matchingRequest.getSickId(),matchingRequest.getRequestType(),matchingRequest.getRequestId(),matchingRequest.getStartTime(),matchingRequest.getEndTime());
        return new CreateMatchingResponse("success");
    }


    @Setter
    @Getter
    @ToString
    static class MatchingRequest{
        private int sickId;
        private int requestType;
        private int requestId;
        private Timestamp startTime;
        private Timestamp endTime;
        public MatchingRequest(){

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
    static class CreateMatchingResponse{
        private String result;

        public CreateMatchingResponse(String result) {
            this.result = result;
        }
    }
}
