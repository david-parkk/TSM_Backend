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
}
