package TSM.demo.repository.query;

import lombok.*;

@Getter
@Setter
@ToString
public class UserHealthDto {


    private int walk;

    private int see;

    private int talk;

    private int listen;


    private int depression;


    private int bipolarDisorder;


    private int iq;

    public UserHealthDto(int walk, int see, int talk, int listen, int depression, int bipolarDisorder, int iq) {
        this.walk = walk;
        this.see = see;
        this.talk = talk;
        this.listen = listen;
        this.depression = depression;
        this.bipolarDisorder = bipolarDisorder;
        this.iq = iq;
    }
}
