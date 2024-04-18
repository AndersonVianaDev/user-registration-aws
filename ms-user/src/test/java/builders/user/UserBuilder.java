package builders.user;

import com.anderson.msuser.api.validation.UserRequestDTO;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.enums.UserType;
import com.anderson.msuser.core.user.model.User;

import java.util.UUID;

public class UserBuilder {

    public static UserRequestDTO toUserRequestDTO() {
        return new UserRequestDTO("Anderson", "anderson@gmail.com", "Anderson");
    }
    public static UserDTO toUserDTO() {
        return new UserDTO("Anderson", "anderson@gmail.com", "Anderson");
    }
    public static User toUser() {
        return new User(UUID.randomUUID(), "Anderson", "anderson@gmail.com", "Anderson", UserType.ADMIN);
    }
}
