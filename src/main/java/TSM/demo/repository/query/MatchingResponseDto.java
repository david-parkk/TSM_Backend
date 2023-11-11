package TSM.demo.repository.query;

import TSM.demo.domain.State;
import ch.qos.logback.core.status.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class MatchingResponseDto {

    private String name;

    private int requestType;

    private Timestamp startTime;

    private Timestamp end_time;
    private int isVolunteer;
    private int id;

    private int groupId;

    private int sickId;

    private int volunteerId;

    private State state;

    public MatchingResponseDto(String name, int requestType, Timestamp startTime, Timestamp end_time, int isVolunteer, int id, int groupId, int sickId, int volunteerId, State state) {
        this.name = name;
        this.requestType = requestType;
        this.startTime = startTime;
        this.end_time = end_time;
        this.isVolunteer = isVolunteer;
        this.id = id;
        this.groupId = groupId;
        this.volunteerId=volunteerId;
        this.sickId=sickId;
        this.state = state;
    }




}




