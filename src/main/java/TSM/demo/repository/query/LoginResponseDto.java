package TSM.demo.repository.query;

import TSM.demo.domain.UserHealth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponseDto {
    private String email;
    private String oauthId;
    private String name;
    private UserHealth userHealth;

    public LoginResponseDto(String email, String oauthId, String name, UserHealth userHealth) {
        this.email = email;
        this.oauthId = oauthId;
        this.name = name;
        this.userHealth = userHealth;
    }
}
