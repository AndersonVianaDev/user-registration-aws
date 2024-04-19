package builders.auth;

import com.anderson.msuser.api.validation.LoginRequestDTO;
import com.anderson.msuser.core.auth.dtos.LoginDTO;

public class AuthBuilder {

    public static LoginRequestDTO toLoginRequestDTO() {
        return new LoginRequestDTO("anderson@gmail.com", "Anderson");
    }

    public static LoginDTO toLoginDTO() {
        return new LoginDTO("anderson@gmail.com", "Anderson");
    }
}
