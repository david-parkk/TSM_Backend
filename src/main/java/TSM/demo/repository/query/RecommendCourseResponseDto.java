package TSM.demo.repository.query;
import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RecommendCourseResponseDto {

    private String name;
    private String url;
    private int id;

    public RecommendCourseResponseDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public RecommendCourseResponseDto(String name, String url,int id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }


}
