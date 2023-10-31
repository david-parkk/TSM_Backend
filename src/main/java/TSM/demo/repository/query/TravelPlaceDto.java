package TSM.demo.repository.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelPlaceDto {

    private String name;

    private String description;

    private String url;

    public TravelPlaceDto(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
