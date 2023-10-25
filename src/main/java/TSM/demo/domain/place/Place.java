package TSM.demo.domain.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@MappedSuperclass
public abstract class Place {



    @Column(name = "name")
    @Size(min=2,max=50)
    private String name;

    @Column(name = "description")
    @Size(min=2,max=2000)
    private String description;
    @Column(name = "url")
    @Size(min=2,max=2000)
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;



}
