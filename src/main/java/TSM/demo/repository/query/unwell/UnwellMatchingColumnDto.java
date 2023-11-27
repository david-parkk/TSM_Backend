package TSM.demo.repository.query.unwell;

import TSM.demo.domain.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UnwellMatchingColumnDto {

    private String volunteerName;

    private int volunteerId;

    private State state;

    //matching id
    private int id;
    //matching groupid
    private int groupId;

    public UnwellMatchingColumnDto(String volunteerName, int volunteerId, State state, int id, int groupId) {
        this.volunteerName = volunteerName;
        this.volunteerId = volunteerId;
        this.state = state;
        this.id = id;
        this.groupId = groupId;
    }
}
