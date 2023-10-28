package TSM.demo.domain.place;

import jakarta.persistence.*;

@Entity(name = "room")
public class Room extends Place{

    public Room(){

    }
    public Room(String name, String description, String url) {
        super(name, description, url);
    }
}
