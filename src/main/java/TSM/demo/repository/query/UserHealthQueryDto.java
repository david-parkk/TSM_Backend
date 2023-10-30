package TSM.demo.repository.query;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserHealthQueryDto {


    private int userHealthId;


    private int walk;

    private int see;

    private int talk;

    private int listen;


    private int depression;


    private int bioplarDisorder;


    private int iq;

}
