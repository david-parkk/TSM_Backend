package TSM.demo.repository.query;

import TSM.demo.domain.place.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantQueryDto {


    private int restaurantId;

    private String name;

    private String description;

    private String url;

    public RestaurantQueryDto(int restaurantId, String name, String description, String url) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
