package TSM.demo.controller;


import TSM.demo.domain.Matching;
import TSM.demo.domain.place.*;
import TSM.demo.repository.PlaceRepository;
import TSM.demo.repository.query.MatchingDto;

import TSM.demo.service.CourseService;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;


import TSM.demo.service.MatchingService;
import TSM.demo.service.PlaceService;
import TSM.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {
    private final MatchingService matchingService;
    private final PlaceService placeService;
    private final CourseService courseService;
    private final UserService userService;
    @GetMapping()
    public ModelAndView volunteerMatching(ModelAndView mav) {
        mav.setViewName("volunteer_matching");
        return mav;
    }

    @PostMapping("/matchings")
    public List<MatchingDto> showAllMatchingById(@RequestBody HashMap<String, Integer> map) {
        List<Matching> matchings=new ArrayList<>();
        List<MatchingDto>matchingDtos=new ArrayList<>();
        if(map.get("isVolunteer")==1){
            matchings.addAll(matchingService.findAllByVolunteerId(map.get("id")));

        }
        else{

        }
        for(Matching matching:matchings){
            matchingDtos.add(new MatchingDto(matching));
        }
        List<Course>courses=courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces=placeService.findAllTravelPlaceByMatching(matchings);
        for(MatchingDto matchingDto:matchingDtos){
            if(matchingDto.getRequest_type()==1){
                for(Course course: courses){
                    if(course.getId()==matchingDto.getRequest_id()){
                        matchingDto.setCourse(course);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==2){
                for(Restaurant restaurant:restaurants){
                    if(restaurant.getId()==matchingDto.getRequest_id()){
                        matchingDto.setRestaurant(restaurant);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==3){
                for(Room room:rooms){
                    if(room.getId()==matchingDto.getRequest_id()){
                        matchingDto.setRoom(room);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==4){
                for(Transport transport:transports){
                    if(transport.getId()==matchingDto.getRequest_id()){
                        matchingDto.setTransport(transport);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==5){
                for(TravelPlace travelPlace:travelPlaces){
                    if(travelPlace.getId()==matchingDto.getRequest_id()){
                        matchingDto.setTravelPlace(travelPlace);
                        break;
                    }
                }
            }
        }

        return matchingDtos;
    }

    @PostMapping("/select")
    public CreateMatchingResponse CreateMatchingRequest(@RequestBody @Valid MatchingSelect matchingSelect) {
        if(matchingSelect.getIsVolunteer()==1) {
            matchingService.addMatchingByUnwell(matchingSelect.getUserId(), matchingSelect.getMatchingGroupId());

        }

        return new CreateMatchingResponse("success");
    }

    @PostMapping("/approve")
    public CreateMatchingResponse selectMatchingRequest(@RequestBody HashMap<String, Integer> map) {
        if(map.get("isVolunteer")==0)
            matchingService.selectMatchingByVolunteer(map.get("userId"), map.get("Group_id"));
        return new CreateMatchingResponse("success");
    }

    @GetMapping("/matchings")
    public List<MatchingDto> requestHelp(@RequestParam int walk,
                              @RequestParam int see,
                              @RequestParam int talk,
                              @RequestParam int listen,
                              @RequestParam int depression,
                              @RequestParam int bipolar_disorder){

        List<Matching> matchings = matchingService.findALLUnMatchingByVolunteer();
        List<MatchingDto>matchingDtos=new ArrayList<>();
        for(Matching matching:matchings){
            matchingDtos.add(new MatchingDto(matching));
        }
        List<Course>courses=courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces=placeService.findAllTravelPlaceByMatching(matchings);
        for(MatchingDto matchingDto:matchingDtos){
            if(matchingDto.getRequest_type()==1){
                for(Course course: courses){
                    if(course.getId()==matchingDto.getRequest_id()){
                        matchingDto.setCourse(course);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==2){
                for(Restaurant restaurant:restaurants){
                    if(restaurant.getId()==matchingDto.getRequest_id()){
                        matchingDto.setRestaurant(restaurant);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==3){
                for(Room room:rooms){
                    if(room.getId()==matchingDto.getRequest_id()){
                        matchingDto.setRoom(room);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==4){
                for(Transport transport:transports){
                    if(transport.getId()==matchingDto.getRequest_id()){
                        matchingDto.setTransport(transport);
                        break;
                    }
                }
            }
            else if(matchingDto.getRequest_type()==5){
                for(TravelPlace travelPlace:travelPlaces){
                    if(travelPlace.getId()==matchingDto.getRequest_id()){
                        matchingDto.setTravelPlace(travelPlace);
                        break;
                    }
                }
            }
        }
        return matchingDtos;

    }

    //매칭 생성 API
    @PostMapping("/request")
    public String requestHelp(
                              @RequestParam("startDateTime") String startDataTime,
                              @RequestParam("endDateTime") String endDataTime,
                              @RequestParam("typeId") int requestType,
                              @RequestParam("uId") int requestId,
                              HttpSession httpSession) {

        System.out.println("startTime = " + startDataTime);
        System.out.println("endTime = " + endDataTime);
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        UserHealth userHealth = (UserHealth) httpSession.getAttribute("userHealth");
        matchingService.requestHelpByUnwell(finduser, requestType, Timestamp.valueOf(startDataTime), Timestamp.valueOf(endDataTime), requestId, userHealth);
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
        public MatchingSelect(){

        }
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
