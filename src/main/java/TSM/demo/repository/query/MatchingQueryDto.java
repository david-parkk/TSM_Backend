package TSM.demo.repository.query;

import TSM.demo.domain.State;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class MatchingQueryDto {

    private int id;


    private int groupId;


    private State state;


    private int sickId ;

    private int volunteerId ;


    private Timestamp startTime;


    private Timestamp end_time;


    private int request_type;


    private int request_id;



    private UserHealth userHealth;

    private Course course;
    public MatchingQueryDto(){

    }

    public MatchingQueryDto(int id, int groupId, State state, int sickId, int volunteerId, Timestamp startTime, Timestamp end_time, int request_type, int request_id) {
        this.id = id;
        this.groupId = groupId;
        this.state = state;
        this.sickId = sickId;
        this.volunteerId = volunteerId;
        this.startTime = startTime;
        this.end_time = end_time;
        this.request_type = request_type;
        this.request_id = request_id;
    }


}
