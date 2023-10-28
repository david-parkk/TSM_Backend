package TSM.demo.domain.place;

import TSM.demo.domain.UserHealth;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "course")
@Getter
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min=2,max=50)
    private String name;

    @Column(name = "region")
    @Size(min=2,max=50)
    private String region;
    @Column(name = "description")
    @Size(min=2,max=2000)
    private String description;
    @Column(name = "url")
    @Size(min=2,max=2000)
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;


    @OneToMany(mappedBy = "course")
    private List<Restaurant> restaurant=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Room> room=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Transport> transports=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Restaurant> tourPlace=new ArrayList<>();

    public Course(){

    }

    public Course(String name, String region, String description, String url) {
        this.name = name;
        this.region = region;
        this.description = description;
        this.url = url;
    }
}
