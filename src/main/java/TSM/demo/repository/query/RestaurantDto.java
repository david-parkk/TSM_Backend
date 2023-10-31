package TSM.demo.repository.query;

import TSM.demo.domain.place.Course;
import TSM.demo.domain.place.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantDto {

    private String name;

    private String description;

    private String url;

    public RestaurantDto( String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
