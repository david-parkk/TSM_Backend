package TSM.demo.repository.query;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CourseQueryDto {

    private int courseId;

    private String name;


    private String region;

    private String description;

    private String url;

    private UserHealthQueryDto userHealth;

    private List<RestaurantQueryDto> restaurantList;


    private List<RoomQueryDto> roomList;


    private List<TransportQueryDto> transportList;


    private List<TravelPlaceQueryDto> travelPlaces;

    public CourseQueryDto(int courseId, String name, String region, String description, String url) {
        this.courseId = courseId;
        this.name = name;
        this.region = region;
        this.description = description;
        this.url = url;
    }
}
