package TSM.demo.domain.place;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "restaurant")
public class Restaurant extends Place{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
