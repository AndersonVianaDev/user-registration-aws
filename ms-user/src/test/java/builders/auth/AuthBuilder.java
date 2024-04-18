package builders.auth;

import com.anderson.msuser.core.user.dtos.LoginDTO;
import com.anderson.msuser.core.user.dtos.LoginResponseDTO;

public class AuthBuilder {

    public static LoginDTO toLoginDTO() {
        return new LoginDTO("email", "password");
    }

    public static LoginResponseDTO toLoginResponseDTO() {
        return new LoginResponseDTO("token");
    }
}
