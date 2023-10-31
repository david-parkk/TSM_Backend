package TSM.demo.domain.place;

import TSM.demo.repository.query.TravelPlaceDto;
import jakarta.persistence.Entity;

@Entity(name = "travel_place")
public class TravelPlace extends Place{

    public TravelPlace(){

    }
    public TravelPlace(String name, String description, String url) {
        super(name, description, url);
    }
}
