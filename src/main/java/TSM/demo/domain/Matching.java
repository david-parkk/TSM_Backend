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
    private Timestamp end_time;

    @Column(name = "request_type")
    private int request_type;

    @Column(name = "request_id")
    private int request_id;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;

    public Matching() {

    }

    public Matching(@NotNull int groupId, State state, int sickId, @NotNull int volunteerId, Timestamp startTime, Timestamp end_time, int request_type, int request_id) {
        this.groupId = groupId;
        this.state = state;
        this.sickId = sickId;
        this.volunteerId = volunteerId;
        this.startTime = startTime;
        this.end_time = end_time;
        this.request_type = request_type;
        this.request_id = request_id;
    }
    public void setUserHealth(UserHealth userHealth){ this.userHealth=userHealth; }
}