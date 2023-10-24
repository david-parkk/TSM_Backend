package TSM.demo.domain.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Place {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "name")
    @Size(min=2,max=50)
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "url")
    @Size(min=2,max=50)
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;



}
