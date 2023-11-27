package TSM.demo.domain;

import TSM.demo.repository.query.UserHealthDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Entity(name = "user_health")
@Getter
public class UserHealth {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    @NotNull
    private int id;

    @NotNull
    @Column(name = "walk")
    private int walk;
    @NotNull
    @Column(name = "see")
    private int see;
    @NotNull
    @Column(name = "talk")
    private int talk;
    @NotNull
    @Column(name = "listen")
    private int listen;

    @NotNull
    @Column(name = "depression")
    private int depression;

    @NotNull
    @Column(name = "bipolar_disorder")
    private int bipolarDisorder;

    @NotNull
    @Column(name = "iq")
    private int iq;

    
    public UserHealth(){

    }

    public UserHealth(@NotNull int walk, @NotNull int see, @NotNull int talk, @NotNull int listen, @NotNull int depression, @NotNull int bipolarDisorder, @NotNull int iq) {
        this.walk = walk;
        this.see = see;
        this.talk = talk;
        this.listen = listen;
        this.depression = depression;
        this.bipolarDisorder = bipolarDisorder;
        this.iq = iq;
    }
    public UserHealth copyUserHealth(){
        return new UserHealth(this.walk, this.see,this.talk,this.listen,this.depression,this.bipolarDisorder,this.iq);
    }

    public boolean isPossibleCourse(UserHealth courseDifficulty) {
        return this.see >= courseDifficulty.getSee()
                && this.walk >= courseDifficulty.getWalk()
                && this.talk >= courseDifficulty.getTalk()
                && this.listen >= courseDifficulty.getListen()
                && this.iq >= courseDifficulty.getIq()
                && this.bipolarDisorder >= courseDifficulty.getBipolarDisorder()
                && this.depression >= courseDifficulty.getDepression();
    }

    public boolean isPossibleMatching(int walk, int see, int talk, int listen, int iq, int depression, int bipolar_disorder) {
        return this.see <= see
                && this.walk <= walk
                && this.talk <= talk
                && this.listen <= listen
                && this.iq <= iq
                && this.bipolarDisorder <= bipolar_disorder
                && this.depression <= depression;
    }

    public UserHealthDto toDto() {
        return new UserHealthDto(walk, see, talk, listen, depression, bipolarDisorder, iq);
    }
}
