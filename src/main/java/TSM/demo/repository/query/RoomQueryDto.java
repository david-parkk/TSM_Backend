package TSM.demo.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomQueryDto {

    private int roomId;

    private String name;

    private String description;

    private String url;

    public RoomQueryDto(int roomId, String name, String description, String url) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
