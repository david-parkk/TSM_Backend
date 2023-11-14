package TSM.demo.domain.place;


import TSM.demo.repository.query.RoomDto;
import TSM.demo.repository.query.TransportDto;
import jakarta.persistence.*;

@Entity(name = "transport")
public class Transport extends Place{

    public Transport(){

    }
    public Transport(String name, String description, String url) {
        super(name, description, url);
    }
    public TransportDto toDto() {
        return new TransportDto(getId(),getName(), getDescription(), getUrl());
    }
}
