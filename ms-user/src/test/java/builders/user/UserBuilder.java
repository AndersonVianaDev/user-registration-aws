package builders.user;

import com.anderson.msuser.api.validation.UserRequestDTO;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.enums.UserType;
import com.anderson.msuser.core.user.model.User;

import java.util.List;
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
    public static List<User> toUserList() {
        return List.of(new User(UUID.randomUUID(), "AAAAA", "anderson@gmail.com", "Anderson", UserType.COMMON),
                new User(UUID.randomUUID(), "BBBB", "anderson@gmail.com", "Anderson", UserType.COMMON),
                new User(UUID.randomUUID(), "CCCCC", "anderson@gmail.com", "Anderson", UserType.COMMON));
    }
    public static User toUserNumber(int n) {
        return new User(null,"name" + n, "email"+ n +"@gmail.com", "password", UserType.ADMIN);
    }
}
