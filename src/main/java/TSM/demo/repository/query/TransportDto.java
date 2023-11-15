package TSM.demo.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransportDto {

    private int id;

    private String name;

    private String description;

    private String url;

    public TransportDto(int id,String name, String description, String url) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
