package TSM.demo.domain.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class Place {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Place(){

    }
    public Place(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }


}
