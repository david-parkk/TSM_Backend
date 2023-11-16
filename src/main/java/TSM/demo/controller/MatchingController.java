package TSM.demo.controller;


import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.place.*;
import TSM.demo.repository.query.MatchingDto;

import TSM.demo.repository.query.MatchingResponseDto;
import TSM.demo.repository.query.SuccessMatchingDto;
import TSM.demo.service.CourseService;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;


import TSM.demo.service.MatchingService;
import TSM.demo.service.PlaceService;
import TSM.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
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


    //unwell 기존 본인이 신청한 matching 조회, volunteer 기준 본인이 신청한 matching 조회
    @GetMapping()
    public ModelAndView volunteerMatching(ModelAndView mav,HttpSession httpSession) {
        List<Matching> matchings=new ArrayList<>();
        List<MatchingResponseDto> matchingResponseDtos=new ArrayList<>();
        List<SuccessMatchingDto>successMatchingDtos=new ArrayList<>();
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));

        if(finduser.getIsVolunteer()==1){
            matchings.addAll(matchingService.findAllByVolunteerId(finduser.getId()));
        }
        else{
            matchings.addAll(matchingService.findAllByUnwellId(finduser.getId()));
        }
        System.out.println("matchings.size() = " + matchings.size());
        List<Course>courses=courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces=placeService.findAllTravelPlaceByMatching(matchings);
        for(Matching matching:matchings){
            if(matching.getVolunteerId()==0&&finduser.getIsVolunteer()==1)
                continue;
            if(matching.getRequestType()==1){
                for(Course course: courses){
                    if(course.getId()==matching.getRequestId()){

                        matchingResponseDtos.add(new MatchingResponseDto(course.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                 ,matching.getState(),"Course"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==2){
                for(Restaurant restaurant: restaurants){
                    if(restaurant.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(restaurant.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Restaurant"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==3){
                for(Room room: rooms){
                    if(room.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(room.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Room"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==4){
                for(Transport transport: transports){
                    if(transport.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(transport.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Transport"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==5){
                for(TravelPlace travelPlace: travelPlaces){
                    if(travelPlace.getId()==matching.getRequestId()){

                        matchingResponseDtos.add(new MatchingResponseDto(travelPlace.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Travel_Place"));
                        break;
                    }
                }
            }
        }
        for (MatchingResponseDto matchingResponseDto: matchingResponseDtos){
            if(matchingResponseDto.getVolunteerId()==0)
                continue;
            User findUser = userService.findOne(matchingResponseDto.getVolunteerId());
            matchingResponseDto.setVolunteerName(findUser.getName());

        }
        System.out.println("matchingResponseDtos.size() = " + matchingResponseDtos.size());
        for (MatchingResponseDto matchingResponseDto : matchingResponseDtos) {
            //System.out.println("matchingResponseDto.getRequestString() = " + matchingResponseDto.getRequestString());
            if(finduser.getIsVolunteer()==0){
                User user = userService.findOne(matchingResponseDto.getVolunteerId());
                if(user==null)
                    continue;
                matchingResponseDto.setVolunteerInfo(user.getName(), user.getEmail(), user.getPhoneNum());
                matchingResponseDto.setSickName(finduser.getName());
                if(matchingResponseDto.getState()== State.SUCCESS){
                    successMatchingDtos.add(new SuccessMatchingDto(matchingResponseDto.getName(),matchingResponseDto.getRequestString(),
                            matchingResponseDto.getStartTime(),matchingResponseDto.getEndTime(),user.getName(),user.getEmail(),user.getPhoneNum(),matchingResponseDto.getGroupId()));
                }

            }
            else{
                User user = userService.findOne(matchingResponseDto.getSickId());
                matchingResponseDto.setUnwellInfo(user.getName(), user.getEmail(), user.getPhoneNum());
                matchingResponseDto.setVolunteerName(finduser.getName());
            }
        }
        mav.addObject("matchings",matchingResponseDtos);
        mav.addObject("successMatchings",successMatchingDtos);
        if(finduser.getIsVolunteer()==0)
            mav.setViewName("unwell_matching");
        else
            mav.setViewName("volunteer_matching_result");

        return mav;
    }
    //volunteer matching 검색 뷰를 위한 api
    @GetMapping("/volunteer/matching/view")
    public ModelAndView MatchingView(ModelAndView mav,HttpSession httpSession) {
        System.out.println("간다");
        mav.setViewName("volunteer_matching");
        return mav;

    }
    // id와 관련된 모든 matching list return
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
            if(matchingDto.getRequestType()==1){
                for(Course course: courses){
                    if(course.getId()==matchingDto.getRequestId()){
                        matchingDto.setCourse(course);
                        matchingDto.setRequestString("Course");
                        break;
                    }
                }
            }
            else if(matchingDto.getRequestType()==2){
                for(Restaurant restaurant:restaurants){
                    if(restaurant.getId()==matchingDto.getRequestId()){
                        matchingDto.setRestaurant(restaurant);
                        matchingDto.setRequestString("Restaurant");
                        break;
                    }
                }
            }
            else if(matchingDto.getRequestType()==3){
                for(Room room:rooms){
                    if(room.getId()==matchingDto.getRequestId()){
                        matchingDto.setRoom(room);
                        matchingDto.setRequestString("Room");
                        break;
                    }
                }
            }
            else if(matchingDto.getRequestType()==4){
                for(Transport transport:transports){
                    if(transport.getId()==matchingDto.getRequestId()){
                        matchingDto.setTransport(transport);
                        matchingDto.setRequestString("Transport");
                        break;
                    }
                }
            }
            else if(matchingDto.getRequestType()==5){
                for(TravelPlace travelPlace:travelPlaces){
                    if(travelPlace.getId()==matchingDto.getRequestId()){
                        matchingDto.setTravelPlace(travelPlace);
                        matchingDto.setRequestString("Travel_Place");
                        break;
                    }
                }
            }
        }

        return matchingDtos;
    }


    @PostMapping("/select")
    public String CreateMatchingRequest(@RequestParam("groupId") int matchingGroupId, @RequestParam("isVolunteer") int isVolunteer, HttpSession httpSession, HttpServletResponse response) throws IOException {
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        if(isVolunteer==1) {
            matchingService.addMatchingByUnwell(finduser.getId(), matchingGroupId);

        }
        response.sendRedirect("/matching/volunteer/matching/view");
        return "success";
    }

    @PostMapping("/approve")
    public CreateMatchingResponse selectMatchingRequest(@RequestParam("groupId") int groupId,@RequestParam("userId")int userId,
                                                        @RequestParam("isVolunteer") int isVolunteer,HttpServletResponse response) throws IOException {
        if(isVolunteer==0) {
            matchingService.selectMatchingByVolunteer(userId, groupId);
            response.sendRedirect("/matching");
        }
        return new CreateMatchingResponse("success");
    }

    //volunteer matching 검색 기능
    @GetMapping("/list")
    public ModelAndView requestHelp(@RequestParam("walk") int walk,
                             @RequestParam("see") int see,
                             @RequestParam("talk") int talk,
                             @RequestParam("listen") int listen,
                             @RequestParam("depression") int depression,
                             @RequestParam("bipolar_disorder") int bipolar_disorder,
                             ModelAndView mav, HttpSession httpSession){

        List<Matching> matchings1 = matchingService.findAllUnMatchingByVolunteer();
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        List<MatchingResponseDto> matchingResponseDtos=new ArrayList<>();
        List<Matching> matchings=new ArrayList<>();
        System.out.println("matchings = " + matchings1.size());
        for(Matching matching:matchings1){
            if(matching.getUserHealth().isPossibleCourse(walk,see,talk,listen,bipolar_disorder,depression)==false)
                matchings.add(matching);
        }
        List<Course>courses=courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces=placeService.findAllTravelPlaceByMatching(matchings);
        System.out.println("matchings = " + matchings.size());
        for(Matching matching:matchings){
            if(matching.getRequestType()==1){

                for(Course course: courses){
                    if(course.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(course.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Course"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==2){
                for(Restaurant restaurant: restaurants){
                    if(restaurant.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(restaurant.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Restaurant"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==3){
                for(Room room: rooms){
                    if(room.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(room.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Room"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==4){
                for(Transport transport: transports){
                    if(transport.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(transport.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"Transport"));
                        break;
                    }
                }
            }
            else if(matching.getRequestType()==5){
                for(TravelPlace travelPlace: travelPlaces){
                    if(travelPlace.getId()==matching.getRequestId()){
                        matchingResponseDtos.add(new MatchingResponseDto(travelPlace.getName(),matching.getRequestType(),matching.getStartTime(),matching.getEndTime(),finduser.getIsVolunteer(),matching.getId(),matching.getGroupId(),matching.getSickId(),matching.getVolunteerId()
                                ,matching.getState(),"TravelPlace"));
                        break;
                    }
                }
            }
        }
        for (MatchingResponseDto matchingResponseDto: matchingResponseDtos){
            /*if(matchingResponseDto.getVolunteerId()==0)
                continue;*/
            User findUser = userService.findOne(matchingResponseDto.getSickId());
            matchingResponseDto.setSickName(findUser.getName());
            if(matchingResponseDto.getRequestType()==1){
                matchingResponseDto.setRequestString("Course");
            }
            else if(matchingResponseDto.getRequestType()==2){
                matchingResponseDto.setRequestString("Restaurant");
            }
            else if(matchingResponseDto.getRequestType()==3){
                matchingResponseDto.setRequestString("Room");
            }
            else if(matchingResponseDto.getRequestType()==4){
                matchingResponseDto.setRequestString("Transport");
            }
            else if(matchingResponseDto.getRequestType()==5){
                matchingResponseDto.setRequestString("Travel_Place");
            }

        }

        System.out.println("matchings = " + matchings.size());
        mav.addObject("matchings",matchingResponseDtos);

        mav.setViewName("volunteer_matching");
        return mav;
    }

    //매칭 생성 API
    @PostMapping("/request")
    public String requestHelp(
                              @RequestParam("startDateTime") String startDataTime,
                              @RequestParam("endDateTime") String endDataTime,
                              @RequestParam("typeId") int requestType,
                              @RequestParam("uId") int requestId,
                              HttpSession httpSession,ModelAndView mav,HttpServletResponse response) throws IOException {
        System.out.println("good");

        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        UserHealth userHealth = (UserHealth) httpSession.getAttribute("userHealth");
        matchingService.requestHelpByUnwell(finduser, requestType, Timestamp.valueOf(startDataTime), Timestamp.valueOf(endDataTime), requestId, userHealth);
        System.out.println("good");
        response.sendRedirect("/matching");
        return "success";
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
