package TSM.demo.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransportQueryDto {

    private int transportId;

    private String name;

    private String description;

    private String url;
}
