package TSM.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Timestamp;

@Entity(name = "matching")
class Matching {

    @Id
    @Column(name = "matching_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "matching_group_id")
    @NotNull
    private int groupId;

    @Column(name = "state")
    private String state;

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


    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;
}