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

    private int id;

    private String name;

    private String description;

    private String url;

    public RestaurantDto( int id,String name, String description, String url) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
