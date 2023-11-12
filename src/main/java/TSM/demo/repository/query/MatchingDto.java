package TSM.demo.repository.query;

import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class MatchingDto {

    private int id;

    private int groupId;

    private State state;

    private int sickId ;

    private int volunteerId ;

    private Timestamp startTime;

    private Timestamp endTime;

    private int requestType;

    private int requestId;

    private Course course;
    private Restaurant restaurant;
    private Room room;
    private Transport transport;
    private TravelPlace travelPlace;

    private UserHealth userHealth;
    public MatchingDto(Matching matching){
        this.id = matching.getId();
        this.groupId = matching.getGroupId();
        this.state = matching.getState();
        this.sickId = matching.getSickId();
        this.volunteerId = matching.getVolunteerId();
        this.startTime = matching.getStartTime();
        this.endTime = matching.getEndTime();
        this.requestType = matching.getRequestType();
        this.requestId = matching.getRequestId();

    }

    public MatchingDto(int id, int groupId, State state, int sickId, int volunteerId, Timestamp startTime, Timestamp end_time, int request_type, int request_id,UserHealth userHealth) {
        this.id = id;
        this.groupId = groupId;
        this.state = state;
        this.sickId = sickId;
        this.volunteerId = volunteerId;
        this.startTime = startTime;
        this.endTime = end_time;
        this.requestType = request_type;
        this.requestId = request_id;
        this.userHealth=userHealth;
    }
}
