package TSM.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @Column(name = "bioplar_disorder")
    private int bioplarDisorder;

    @NotNull
    @Column(name = "iq")
    private int iq;

    public UserHealth(){

    }

    public UserHealth(@NotNull int walk, @NotNull int see, @NotNull int talk, @NotNull int listen, @NotNull int depression, @NotNull int bioplarDisorder, @NotNull int iq) {
        this.walk = walk;
        this.see = see;
        this.talk = talk;
        this.listen = listen;
        this.depression = depression;
        this.bioplarDisorder = bioplarDisorder;
        this.iq = iq;
    }


}
