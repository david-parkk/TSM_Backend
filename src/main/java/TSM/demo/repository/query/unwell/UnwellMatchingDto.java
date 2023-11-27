package TSM.demo.repository.query.unwell;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UnwellMatchingDto {
    //course name (room name, travel_place name이 될 수 있음)
    private String name;

    private int requestType;

    private String requestString;

    private Timestamp startTime;

    private Timestamp endTime;

    //matching groupid
    private int groupId;

    private List<UnwellMatchingColumnDto> unwellMatchingColumnDtoList=new ArrayList<>();

    public UnwellMatchingDto(String name, int requestType, String requestString, Timestamp startTime, Timestamp endTime, int groupId) {
        this.name = name;
        this.requestType = requestType;
        this.requestString = requestString;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
    }
}
