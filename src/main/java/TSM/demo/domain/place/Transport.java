package TSM.demo.domain.place;


import jakarta.persistence.*;

@Entity(name = "transport")
public class Transport extends Place{

    public Transport(){

    }
    public Transport(String name, String description, String url) {
        super(name, description, url);
    }
}
