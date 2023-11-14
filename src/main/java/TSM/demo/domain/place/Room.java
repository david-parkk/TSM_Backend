package TSM.demo.domain.place;

import TSM.demo.repository.query.RoomDto;
import jakarta.persistence.*;

@Entity(name = "room")
public class Room extends Place{

    public Room(){

    }
    public Room(String name, String description, String url) {
        super(name, description, url);
    }

    public RoomDto toDto() {
        return new RoomDto(getId(),getName(), getDescription(), getUrl());
    }
}
