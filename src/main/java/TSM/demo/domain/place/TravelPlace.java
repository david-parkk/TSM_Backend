package TSM.demo.domain.place;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "travel_place")
public class TravelPlace extends Place{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
