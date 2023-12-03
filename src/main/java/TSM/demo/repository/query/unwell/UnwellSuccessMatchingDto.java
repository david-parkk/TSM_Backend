package TSM.demo.repository.query.unwell;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UnwellSuccessMatchingDto {

    private String name;

    private String requestString;

    private Timestamp startDate;

    private Timestamp endDate;

    private String volunteerName;

    private String volunteerEmail;

    private String volunteerPhoneNum;

    private String sickName;

    private String sickEmail;

    private String sickPhoneNum;

    private int groupId;

    private boolean isRated;

    public UnwellSuccessMatchingDto(String courseName, String requestTypeString, Timestamp startDate, Timestamp endDate, String volunteerName, String volunteerEmail, String volunteerPhoneNum, int groupId, boolean isRated) {
        this.name = courseName;
        this.requestString = requestTypeString;
        this.startDate = startDate;
        this.endDate = endDate;
        this.volunteerName = volunteerName;
        this.volunteerEmail = volunteerEmail;
        this.volunteerPhoneNum = volunteerPhoneNum;
        this.groupId=groupId;
        this.isRated = isRated;
    }
}
