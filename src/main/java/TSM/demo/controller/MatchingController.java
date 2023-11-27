package TSM.demo.controller;


import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.place.*;

import TSM.demo.repository.query.MatchingResponseDto;
import TSM.demo.repository.query.unwell.UnwellSuccessMatchingDto;
import TSM.demo.repository.query.unwell.UnwellMatchingColumnDto;
import TSM.demo.repository.query.unwell.UnwellMatchingDto;
import TSM.demo.service.CourseService;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;


import TSM.demo.service.MatchingService;
import TSM.demo.service.PlaceService;
import TSM.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {
    private final MatchingService matchingService;
    private final PlaceService placeService;
    private final CourseService courseService;
    private final UserService userService;


    public List<MatchingResponseDto> findUnwellMatchingResponseDto(User unwell){
        List<Matching> matchings = new ArrayList<>();
        matchings.addAll(matchingService.findAllByUnwellId(unwell.getId()));
        List<Course> courses = courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces = placeService.findAllTravelPlaceByMatching(matchings);

        List<MatchingResponseDto>matchingResponseDtos=new ArrayList<>();
        for (Matching matching : matchings) {
            if (matching.getRequestType() == 1) {
                for (Course course : courses) {
                    if (course.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(course.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), unwell.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Course"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 2) {
                for (Restaurant restaurant : restaurants) {
                    if (restaurant.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(restaurant.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), unwell.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Restaurant"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 3) {
                for (Room room : rooms) {
                    if (room.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(room.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), unwell.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Room"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 4) {
                for (Transport transport : transports) {
                    if (transport.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(transport.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), unwell.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Transport"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 5) {
                for (TravelPlace travelPlace : travelPlaces) {
                    if (travelPlace.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(travelPlace.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), unwell.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Travel_Place"));
                        break;
                    }
                }
            }
        }
        for (MatchingResponseDto matchingResponseDto : matchingResponseDtos) {
            if (matchingResponseDto.getVolunteerId() == 0)
                continue;
            User findUser = userService.findOne(matchingResponseDto.getVolunteerId());
            matchingResponseDto.setVolunteerName(findUser.getName());
        }
        return matchingResponseDtos;
    }

    public List<MatchingResponseDto> addMemberInfoUnwellMatchingResponseDto(List<MatchingResponseDto>matchingResponseDtos,User unwell){
        for (MatchingResponseDto matchingResponseDto : matchingResponseDtos) {
            User volunteer = userService.findOne(matchingResponseDto.getVolunteerId());
            if (volunteer == null)
                continue;
            matchingResponseDto.setVolunteerInfo(volunteer.getName(), volunteer.getEmail(), volunteer.getPhoneNum());
            matchingResponseDto.setSickName(unwell.getName());
        }
        return matchingResponseDtos;
    }

    public List<UnwellSuccessMatchingDto> findUnwellSuccessMatchingDto(List<MatchingResponseDto> matchingResponseDtos,User unwell){
        List<UnwellSuccessMatchingDto> unwellSuccessMatchingDtos=new ArrayList<>();
        for (MatchingResponseDto matchingResponseDto : matchingResponseDtos) {
            if (matchingResponseDto.getState() == State.SUCCESS) {
                unwellSuccessMatchingDtos.add(new UnwellSuccessMatchingDto(matchingResponseDto.getName(), matchingResponseDto.getRequestString(),
                        matchingResponseDto.getStartTime(), matchingResponseDto.getEndTime(), matchingResponseDto.getVolunteerName(), matchingResponseDto.getVolunteerName(), matchingResponseDto.getVolunteerPhoneNum(), matchingResponseDto.getGroupId()));
            }

        }
        return unwellSuccessMatchingDtos;
    }

    @GetMapping("/unwell")
    public ModelAndView unwellMatching(ModelAndView mav, HttpSession httpSession) {

        List<UnwellMatchingDto>unwellMatchingDtos=new ArrayList<>();
        List<UnwellSuccessMatchingDto> successMatchingDtos = new ArrayList<>();
        //본인 유저정보 찾기
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        //본인과 관련된 matching 정보 받기
        List<MatchingResponseDto> matchingResponseDtos=findUnwellMatchingResponseDto(finduser);
        //matchingResponseDto에 unwell volunteer정보 초기화
        List<MatchingResponseDto> newMatchingResponseDtos=addMemberInfoUnwellMatchingResponseDto(matchingResponseDtos,finduser);

        boolean isInsert;
        //받아온 matchingResponseDto를 가지고 UnwellMatchingDto생성
        for (MatchingResponseDto matching: newMatchingResponseDtos){
            isInsert=false;
            //이미 처리된 matching 정보는 건너뜀
            if(matching.getState()==State.SUCCESS||matching.getState()== State.FAIL)
                continue;
            for(UnwellMatchingDto unwellMatchingDto: unwellMatchingDtos){
                if(unwellMatchingDto.getGroupId()==matching.getGroupId()){
                    unwellMatchingDto.getUnwellMatchingColumnDtoList().add(new UnwellMatchingColumnDto(matching.getVolunteerName(),matching.getVolunteerId(),matching.getState(),matching.getId(),matching.getGroupId()));
                    isInsert=true;
                    break;
                }
            }
            if(!isInsert){
                UnwellMatchingDto unwellMatchingDto = new UnwellMatchingDto(matching.getName(), matching.getRequestType(), matching.getRequestString(), matching.getStartTime(), matching.getEndTime(), matching.getGroupId());
                unwellMatchingDtos.add(unwellMatchingDto);
                if(matching.getVolunteerId()!=0){
                    unwellMatchingDto.getUnwellMatchingColumnDtoList().add(new UnwellMatchingColumnDto(matching.getVolunteerName(),matching.getVolunteerId(),matching.getState(),matching.getId(),matching.getGroupId()));
                }
            }
        }
        //성공한 매칭 따로 가져오기
        successMatchingDtos=findUnwellSuccessMatchingDto(newMatchingResponseDtos,finduser);

        mav.addObject("matchings", unwellMatchingDtos);
        mav.addObject("successMatchings", successMatchingDtos);
        mav.setViewName("unwell_matching");

        return mav;
    }

    public List<MatchingResponseDto> findVolunteerMatchingResponseDto(User volunteer,List<Matching>matchings){


        List<MatchingResponseDto> matchingResponseDtos = new ArrayList<>();
        List<Course> courses = courseService.findAllCourseByMatching(matchings);
        List<Restaurant> restaurants = placeService.findAllRestaurantByMatching(matchings);
        List<Room> rooms = placeService.findAllRoomByMatching(matchings);
        List<Transport> transports = placeService.findAllTransportByMatching(matchings);
        List<TravelPlace> travelPlaces = placeService.findAllTravelPlaceByMatching(matchings);

        for (Matching matching : matchings) {
            if (matching.getRequestType() == 1) {

                for (Course course : courses) {
                    if (course.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(course.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), volunteer.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Course"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 2) {
                for (Restaurant restaurant : restaurants) {
                    if (restaurant.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(restaurant.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), volunteer.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Restaurant"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 3) {
                for (Room room : rooms) {
                    if (room.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(room.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), volunteer.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Room"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 4) {
                for (Transport transport : transports) {
                    if (transport.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(transport.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), volunteer.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "Transport"));
                        break;
                    }
                }
            } else if (matching.getRequestType() == 5) {
                for (TravelPlace travelPlace : travelPlaces) {
                    if (travelPlace.getId() == matching.getRequestId()) {
                        matchingResponseDtos.add(new MatchingResponseDto(travelPlace.getName(), matching.getRequestType(), matching.getStartTime(), matching.getEndTime(), volunteer.getIsVolunteer(), matching.getId(), matching.getGroupId(), matching.getSickId(), matching.getVolunteerId()
                                , matching.getState(), "TravelPlace"));
                        break;
                    }
                }
            }
        }
        for (MatchingResponseDto matchingResponseDto : matchingResponseDtos) {
            /*if(matchingResponseDto.getVolunteerId()==0)
                continue;*/
            User findUser = userService.findOne(matchingResponseDto.getSickId());
            matchingResponseDto.setSickName(findUser.getName());

        }
        return matchingResponseDtos;
    }
    @GetMapping("/volunteer")
    public ModelAndView requestHelp(@RequestParam(value = "walk" ,required = false, defaultValue = "3") int walk,
                                    @RequestParam(value = "see",required = false, defaultValue = "3") int see,
                                    @RequestParam(value = "talk",required = false, defaultValue = "3") int talk,
                                    @RequestParam(value = "listen",required = false, defaultValue = "3") int listen,
                                    @RequestParam(value = "iq",required = false, defaultValue = "3") int iq,
                                    @RequestParam(value = "depression",required = false, defaultValue = "3") int depression,
                                    @RequestParam(value = "bipolar_disorder",required = false, defaultValue = "3") int bipolar_disorder,
                                    ModelAndView mav, HttpSession httpSession) {
        //matching완료되지 않은 모든 matching가져옴
        List<Matching> matchings1 = matchingService.findAllUnMatchingByVolunteer();
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        List<Matching> matchings = new ArrayList<>();
        //검색 범위에 알맞는 matching 정렬
        for (Matching matching : matchings1) {
            if (matching.getUserHealth().isPossibleMatching(walk, see, talk, listen, iq, bipolar_disorder, depression) == true)
                matchings.add(matching);
        }
        //matching 정보설정
        List<MatchingResponseDto> matchingResponseDtos = findVolunteerMatchingResponseDto(finduser,matchings);

        mav.addObject("matchings", matchingResponseDtos);

        mav.setViewName("volunteer_matching");
        return mav;
    }

    @GetMapping("/volunteer_result")
    public ModelAndView volunteerMatching(ModelAndView mav, HttpSession httpSession) {
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        List<Matching> matchings = matchingService.findAllByVolunteerId(finduser.getId());
        List<MatchingResponseDto> matchingResponseDtos = findVolunteerMatchingResponseDto(finduser,matchings);

        mav.setViewName("volunteer_matching_result");
        return mav;

    }


    @PostMapping("/syn")
    public String requestHelp(
            @RequestParam("startDateTime") String startDataTime,
            @RequestParam("endDateTime") String endDataTime,
            @RequestParam("typeId") int requestType,
            @RequestParam("uId") int requestId,
            HttpSession httpSession, ModelAndView mav, HttpServletResponse response) throws IOException {
        System.out.println("good");

        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        UserHealth userHealth = (UserHealth) httpSession.getAttribute("userHealth");
        matchingService.requestHelpByUnwell(finduser, requestType, Timestamp.valueOf(startDataTime), Timestamp.valueOf(endDataTime), requestId, userHealth);
        System.out.println("good");
        response.sendRedirect("/matching");
        return "success";
    }


    @PostMapping("/synack")
    public String CreateMatchingRequest(@RequestParam("groupId") int matchingGroupId, @RequestParam("isVolunteer") int isVolunteer, HttpSession httpSession, HttpServletResponse response) throws IOException {
        User finduser = userService.findOneByEmail((String) httpSession.getAttribute("email"));
        if (isVolunteer == 1) {
            matchingService.addMatchingByUnwell(finduser.getId(), matchingGroupId);

        }
        response.sendRedirect("/matching/volunteer/matching/view");
        return "success";
    }

    @PostMapping("/ack")
    public CreateMatchingResponse selectMatchingRequest(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId,
                                                        @RequestParam("isVolunteer") int isVolunteer, HttpServletResponse response) throws IOException {
        if (isVolunteer == 0) {
            matchingService.selectMatchingByVolunteer(userId, groupId);
            response.sendRedirect("/matching");
        }
        return new CreateMatchingResponse("success");
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

        public MatchingSelect() {

        }

        public MatchingSelect(int matchingGroupId, int isVolunteer, int userId) {
            this.matchingGroupId = matchingGroupId;
            this.isVolunteer = isVolunteer;
            this.userId = userId;
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
