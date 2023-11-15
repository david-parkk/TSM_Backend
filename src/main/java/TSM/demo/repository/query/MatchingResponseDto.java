package TSM.demo.repository.query;

import TSM.demo.domain.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class MatchingResponseDto {

    private String name;

    private int requestType;

    private String requestString;

    private Timestamp startTime;

    private Timestamp endTime;
    private int isVolunteer;
    private int id;

    private int groupId;

    private int sickId;

    private String sickName;

    private String sickEmail;

    private String sickPhoneNum;

    private int volunteerId;

    private String volunteerName;

    private String volunteerEmail;

    private String volunteerPhoneNum;

    private State state;

    public MatchingResponseDto(String name, int requestType, Timestamp startTime, Timestamp endTime, int isVolunteer, int id, int groupId, int sickId, int volunteerId, State state,String requestString) {
        this.name = name;
        this.requestType = requestType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isVolunteer = isVolunteer;
        this.id = id;
        this.groupId = groupId;
        this.volunteerId=volunteerId;
        this.sickId=sickId;
        this.state = state;
        this.requestString=requestString;
    }

    public void setVolunteerInfo(String name,String email,String phoneNum){
        this.volunteerName=name;
        this.volunteerEmail=email;
        this.volunteerPhoneNum=phoneNum;
    }

    public void setUnwellInfo(String name,String email,String phoneNum){
        this.sickName=name;
        this.sickEmail=email;
        this.sickPhoneNum=phoneNum;
    }




}




