package builders.auth;

import com.anderson.msuser.api.validation.LoginRequestDTO;

public class AuthBuilder {

    public static LoginRequestDTO toLoginDTO() {
        return new LoginRequestDTO("anderson@gmail.com", "Anderson");
    }
}
