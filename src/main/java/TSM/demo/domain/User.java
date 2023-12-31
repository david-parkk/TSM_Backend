package TSM.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
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
    private String phoneNum;

    @Column(name = "is_volunteer")
    private int isVolunteer;

    @Column(name = "name")
    @Size(min=2,max=50)
    private String name;

    @Column(name = "oauth_id")
    @Size(min=2,max=2000)
    private String oauthId;

    @Column(name = "rating")
    private Integer rating;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "health_id")
    private UserHealth userHealth;

    public User(){

    }

    public User(@NotNull String email, @NotNull String phoneNum, int isVolunteer, String name, String oauthId) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.isVolunteer = isVolunteer;
        this.name = name;
        this.oauthId = oauthId;
    }


    /*public UserQueryDto toDto() {
        return UserQueryDto.builder()
                .email(this.email)
                .isVolunteer(this.isVolunteer)
                .name(this.name)
                .phoneNum(this.phoneNum)
                .rating(this.rating)
                .build();
    }*/

//    public UserQueryDto toDto() {
//        return UserQueryDto.builder()
//                .email(this.email)
//                .isVolunteer(this.isVolunteer)
//                .name(this.name)
//                .phoneNum(this.phoneNum)
//                .rating(this.rating)
//                .build();
//    }

}
