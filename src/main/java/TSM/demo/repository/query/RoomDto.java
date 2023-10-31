package TSM.demo.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomDto {

    private String name;

    private String description;

    private String url;

    public RoomDto(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
