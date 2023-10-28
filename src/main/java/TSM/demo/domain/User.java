package TSM.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

@Entity(name = "user")
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotNull
    private int id;


    @Column(name="email")
    @NotNull
    @Size(min=2,max=50)
    private String email;

    @Column(name="phone_num")
    @NotNull
    @Size(min=2,max=50)
    private String phone_num;

    @Column(name = "is_volunteer")
    private boolean isVolunteer;

    @Column(name = "name")
    @Size(min=2,max=50)
    private String name;

    @Column(name = "access_token")
    @Size(min=2,max=2000)
    private String access_token;

    @Column(name = "rating")
    private int rating;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;

    public User(){

    }

    public User(@NotNull String email, @NotNull String phone_num, boolean isVolunteer, String name, String access_token) {
        this.email = email;
        this.phone_num = phone_num;
        this.isVolunteer = isVolunteer;
        this.name = name;
        this.access_token = access_token;
    }


    public void setUserHealth(UserHealth userHealth) {
        this.userHealth = userHealth;
    }
}
