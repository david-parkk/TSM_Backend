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

    private int volunteerId;

    private String volunteerName;

    private State state;

    public MatchingResponseDto(String name, int requestType, Timestamp startTime, Timestamp end_time, int isVolunteer, int id, int groupId, int sickId, int volunteerId, State state) {
        this.name = name;
        this.requestType = requestType;
        this.startTime = startTime;
        this.endTime = end_time;
        this.isVolunteer = isVolunteer;
        this.id = id;
        this.groupId = groupId;
        this.volunteerId=volunteerId;
        this.sickId=sickId;
        this.state = state;
    }




}




