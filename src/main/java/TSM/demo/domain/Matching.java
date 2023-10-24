package TSM.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Timestamp;

@Entity(name = "matching")
class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    @NotNull
    private int id;

    @Column(name = "sick_id")
    private int sick_id ;
    @Column(name = "volunteer_id")
    @NotNull
    private int volunteer_id ;

    @Column(name = "start_time")
    private Timestamp start_time;

    @Column(name = "end_time")
    private Timestamp end_time;

    @Column(name = "require_type")
    private int require_type;

    @Column(name = "require_id")
    private int require_id;


    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;
}