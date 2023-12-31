package TSM.demo.domain.place;

import TSM.demo.domain.UserHealth;
import TSM.demo.repository.query.CourseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "course")
@Getter
@Setter
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;


    @OneToMany(mappedBy = "course")
    private List<Restaurant> restaurantList=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Room> roomList=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Transport> transportList=new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<TravelPlace> travelPlaceList=new ArrayList<>();

    public Course(){

    }

    public Course(String name, String region, String description, String url) {
        this.name = name;
        this.region = region;
        this.description = description;
        this.url = url;
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurantList.add(restaurant);
    }
    public void addRoom(Room room){
        this.roomList.add(room);
    }
    public void addTransport(Transport transport){
        this.transportList.add(transport);
    }
    public void addTravelPlace(TravelPlace travelPlace){ this.travelPlaceList.add(travelPlace); }

    public CourseDto toDto() {
        CourseDto courseDto = new CourseDto(id,name, region, description, url);
        // UserHealth 엔티티를 UserHealthQueryDto로 변환하여 설정
        if (userHealth != null) {
            userHealth =  (UserHealth) Hibernate.unproxy(userHealth);
            courseDto.setUserHealth(userHealth.toDto());
        }
        courseDto.setRestaurantList(restaurantList);
        courseDto.setRoomList(roomList);
        courseDto.setTransportList(transportList);
        courseDto.setTravelPlaces(travelPlaceList);
        return courseDto;
    }
}
