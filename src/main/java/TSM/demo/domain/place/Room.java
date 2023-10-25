package TSM.demo.domain.place;

import jakarta.persistence.*;

@Entity(name = "room")
public class Room extends Place{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
