package TSM.demo.repository.query;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
public class UserHealthQueryDto {


    private int walk;

    private int see;

    private int talk;

    private int listen;


    private int depression;


    private int bipolarDisorder;


    private int iq;

    public UserHealthQueryDto(int walk, int see, int talk, int listen, int depression, int bipolarDisorder, int iq) {
        this.walk = walk;
        this.see = see;
        this.talk = talk;
        this.listen = listen;
        this.depression = depression;
        this.bipolarDisorder = bipolarDisorder;
        this.iq = iq;
    }
}
