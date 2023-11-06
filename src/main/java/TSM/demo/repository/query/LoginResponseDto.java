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
    private UserHealth userHealth;

    public LoginResponseDto(String email, String oauthId, UserHealth userHealth) {
        this.email = email;
        this.oauthId = oauthId;
        this.userHealth = userHealth;
    }
}
