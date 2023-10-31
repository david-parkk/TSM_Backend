package TSM.demo.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelPlaceQueryDto {

    private int travelPlaceId;

    private String name;

    private String description;

    private String url;

    public TravelPlaceQueryDto(int travelPlaceId, String name, String description, String url) {
        this.travelPlaceId = travelPlaceId;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
