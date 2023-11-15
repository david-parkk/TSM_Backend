package TSM.demo.domain.place;

import TSM.demo.repository.query.RestaurantDto;
import jakarta.persistence.*;

@Entity(name = "restaurant")
public class Restaurant extends Place{


    public Restaurant(){

    }
    public Restaurant(String name, String description, String url) {
        super(name, description, url);
    }
    public RestaurantDto toDto() {
        return new RestaurantDto(getId(),getName(), getDescription(), getUrl());
    }
}
