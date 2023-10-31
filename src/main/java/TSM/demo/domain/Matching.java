package TSM.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

import java.sql.Timestamp;

@Entity(name = "matching")
@Getter
public class Matching {

    @Id
    @Column(name = "matching_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "matching_group_id")
    @NotNull
    private int groupId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "sick_id")
    private int sickId ;
    @Column(name = "volunteer_id")
    @NotNull
    private int volunteerId ;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "request_type")
    private int requestType;

    @Column(name = "request_id")
    private int requestId;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;

    public Matching() {

    }

    public Matching(@NotNull int groupId, State state, int sickId, @NotNull int volunteerId, Timestamp startTime, Timestamp endTime, int requestType, int requestId) {
        this.groupId = groupId;
        this.state = state;
        this.sickId = sickId;
        this.volunteerId = volunteerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestType = requestType;
        this.requestId = requestId;
    }
    public Matching(@NotNull int groupId, State state, int sickId, @NotNull int volunteerId, Timestamp startTime, Timestamp endTime, int requestType, int requestId,UserHealth userHealth) {
        this.groupId = groupId;
        this.state = state;
        this.sickId = sickId;
        this.volunteerId = volunteerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestType = requestType;
        this.requestId = requestId;
        this.userHealth=userHealth;
    }
    public void setUserHealth(UserHealth userHealth){ this.userHealth=userHealth; }
}