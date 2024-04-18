package builders.user;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.enums.UserType;
import com.anderson.msuser.core.user.model.User;

import java.util.UUID;

public class UserBuilder {

    public static UserDTO toUserDTO() {
        return new UserDTO("name", "email", "password");
    }
    public static User toUser() {
        return new User(UUID.randomUUID(), "name", "email", "password", UserType.ADMIN);
    }
}
