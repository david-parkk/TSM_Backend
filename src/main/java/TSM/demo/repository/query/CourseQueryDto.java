package TSM.demo.repository.query;


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

    private UserHealthDto userHealth;

    private List<RestaurantDto> restaurantList;


    private List<RoomDto> roomList;


    private List<TransportDto> transportList;


    private List<TravelPlaceDto> travelPlaces;

    public CourseQueryDto(int courseId, String name, String region, String description, String url) {
        this.courseId = courseId;
        this.name = name;
        this.region = region;
        this.description = description;
        this.url = url;
    }
}
